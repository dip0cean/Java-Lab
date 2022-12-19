package com.study.service;

import com.study.domain.entity.Product;
import com.study.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getProducts(Pageable pageable) {
        return productRepository.getProducts(pageable);
    }

    public List<Product> findByContent(String keyword, Pageable pageable) {
        return productRepository.findByContent(keyword, pageable);
    }
}
