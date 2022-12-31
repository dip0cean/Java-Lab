package com.study.repository.query;

import com.study.domain.entity.Product;
import com.study.domain.entity.ProductDiscount;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ProductQueryRepository {

    Slice<Product> findDiscountProducts(ProductDiscount.Type type, Pageable pageable);
}
