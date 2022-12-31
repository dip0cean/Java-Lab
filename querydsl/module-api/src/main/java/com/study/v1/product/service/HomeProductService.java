package com.study.v1.product.service;

import com.study.domain.entity.Product;
import com.study.domain.entity.ProductDiscount;
import com.study.domain.entity.ProductOption;
import com.study.repository.ProductDiscountRepository;
import com.study.repository.ProductOptionRepository;
import com.study.repository.ProductRepository;
import com.study.v1.product.dto.HomeProductDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HomeProductService {

    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;
    private final ProductDiscountRepository productDiscountRepository;

    @Transactional(readOnly = true)
    public Slice<HomeProductDto> findDiscountProducts(ProductDiscount.Type type, Pageable pageable) {
        Slice<ProductDiscount> productDiscounts = productDiscountRepository.findProductDiscountsByLatestStartDateAndType(type, pageable);
        List<String> productIds = productDiscounts.getContent().stream().map(ProductDiscount::getProductId).distinct().toList();
        List<String> productOptionIds = productDiscounts.getContent().stream().map(ProductDiscount::getProductOptionId).distinct().toList();

        List<Product> products = productRepository.findAllById(productIds);
        Map<String, List<ProductOption>> productOptions = productOptionRepository.findAllById(productOptionIds).stream().collect(Collectors.groupingBy(ProductOption::getProductId));
        Map<String, List<ProductDiscount>> productDiscountsByProductOptionId = productDiscounts.getContent().stream().collect(Collectors.groupingBy(ProductDiscount::getProductOptionId));

        productOptions.values().stream().flatMap(Collection::stream).filter(productOption -> productDiscountsByProductOptionId.get(productOption.getId()) != null).forEach(productOption -> productOption.setProductDiscounts(productDiscountsByProductOptionId.get(productOption.getId())));
        products.stream().filter(product -> productOptions.get(product.getId()) != null).forEach(product -> product.setProductOptions(productOptions.get(product.getId())));

        return HomeProductDto.entitiesToDtoList(products, pageable, productDiscounts.hasNext(), false);
    }
}
