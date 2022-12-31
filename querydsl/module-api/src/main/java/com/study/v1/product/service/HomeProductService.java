package com.study.v1.product.service;

import com.study.domain.entity.Product;
import com.study.domain.entity.ProductDiscount;
import com.study.repository.ProductRepository;
import com.study.v1.product.dto.HomeProductDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class HomeProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Slice<HomeProductDto> findDiscountProducts_1(ProductDiscount.Type type, Pageable pageable) {
        Slice<Product> products = productRepository.findDiscountProducts_1(type, pageable);
        return HomeProductDto.entitiesToDtoList(products.getContent(), pageable, products.hasNext(), false);
    }

    @Transactional(readOnly = true)
    public Slice<HomeProductDto> findDiscountProducts_2(ProductDiscount.Type type, Pageable pageable) {
        Slice<Product> products = productRepository.findDiscountProducts_2(type, pageable);
        return HomeProductDto.entitiesToDtoList(products.getContent(), pageable, products.hasNext(), false);
    }
}
