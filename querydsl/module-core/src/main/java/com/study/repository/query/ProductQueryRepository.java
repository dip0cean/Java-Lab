package com.study.repository.query;

import com.study.domain.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductQueryRepository {
    List<Product> getProducts(Pageable pageable);
}
