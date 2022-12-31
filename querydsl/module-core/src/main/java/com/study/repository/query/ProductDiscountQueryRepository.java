package com.study.repository.query;

import com.study.domain.entity.ProductDiscount;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.time.LocalDateTime;

public interface ProductDiscountQueryRepository {

    LocalDateTime findLatestStartDate(ProductDiscount.Type type);

    Slice<ProductDiscount> findProductDiscountsByLatestStartDateAndType(ProductDiscount.Type type, Pageable pageable);
}
