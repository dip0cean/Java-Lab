package com.study.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.domain.entity.Product;
import com.study.repository.query.ProductQueryRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDate;
import java.util.List;

import static com.study.domain.entity.QProduct.product;
import static com.study.domain.entity.QProductBest.productBest;

public class ProductRepositoryImpl extends QuerydslRepositorySupport implements ProductQueryRepository {

    private final JPAQueryFactory queryFactory;

    public ProductRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Product.class);
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

    @Override
    public List<Product> findByContent(String keyword, Pageable pageable) {
        //가장 최근 시작일 조회
        LocalDate latestStartDate = queryFactory
                .select(productBest.startDate)
                .from(productBest)
                .orderBy(productBest.id.desc())
                .limit(1)
                .fetchOne();

        return queryFactory
                .select(productBest.product)
                .from(productBest)
                .innerJoin(productBest.product, product)
                .where(productBest.startDate.eq(latestStartDate),
                        product.content.like("%" + keyword + "%"))
                .orderBy(productBest.sales.desc())
                .limit(pageable.getPageSize() + 1)
                .offset(pageable.getOffset())
                .fetch();
    }
}
