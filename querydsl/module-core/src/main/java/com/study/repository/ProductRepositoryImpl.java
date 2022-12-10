package com.study.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.domain.Product;
import com.study.repository.query.ProductQueryRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.study.domain.QProduct.product;

public class ProductRepositoryImpl implements ProductQueryRepository {

    private final JPAQueryFactory queryFactory;

    public ProductRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Product> getProducts(Pageable pageable) {
        return queryFactory
                .selectFrom(product)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(product.id.desc())
                .fetch();
    }
}
