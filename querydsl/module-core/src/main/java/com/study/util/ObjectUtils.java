package com.study.util;

import lombok.experimental.UtilityClass;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@UtilityClass
public class ObjectUtils {
    public static void copyList(List<Object> sourceList,
                                List<Object> targetList,
                                Class<?> targetType) throws InstantiationException, IllegalAccessException {
        for (Object source : sourceList) {
            Object target = targetType.newInstance();
            copyProperties(source, target);
            targetList.add(target);
        }
    }

    public static <T> void copyProperties(T source, T target) {
        if (source != null && target != null) {
            BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
        }
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            if (src.isWritableProperty(pd.getName())
                    && src.getPropertyValue(pd.getName()) == null) {
                emptyNames.add(pd.getName());
            }
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
