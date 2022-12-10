package com.study.v1.controller;

import com.study.domain.Product;
import com.study.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(@PageableDefault Pageable pageable) {
        List<Product> products = productService.getProducts(pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/content")
    public ResponseEntity<List<Product>> findByContent(@RequestParam String keyword,
                                                       @PageableDefault Pageable pageable) {
        List<Product> products = productService.findByContent(keyword, pageable);
        return ResponseEntity.ok(products);
    }
}
