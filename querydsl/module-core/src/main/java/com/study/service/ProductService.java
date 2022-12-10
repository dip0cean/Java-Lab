package com.study.service;

import com.study.domain.Product;
import com.study.repository.ProductRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepositoryImpl productRepositoryImpl;

    public List<Product> getProducts(Pageable pageable) {
        return productRepositoryImpl.getProducts(pageable);
    }

    public List<Product> findByContent(String keyword, Pageable pageable) {
        return productRepositoryImpl.findByContent(keyword, pageable);
    }
}
