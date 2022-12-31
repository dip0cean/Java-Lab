package com.study.v1.product.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.domain.entity.Product;
import com.study.domain.entity.ProductDiscount;
import com.study.repository.query.ProductQueryRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.study.domain.entity.QProduct.product;
import static com.study.domain.entity.QProductDiscount.productDiscount;

@Repository
public class ProductRepositoryImpl extends QuerydslRepositorySupport implements ProductQueryRepository {

    private final JPAQueryFactory queryFactory;

    public ProductRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Product.class);
        this.queryFactory = queryFactory;
    }

    public Slice<Product> findDiscountProducts(ProductDiscount.Type type, Pageable pageable) {
        // 가장 최근 시작일 조회
        LocalDateTime latestStartDate = this.findLatestStartDate(type);

        // 제품 조회
        List<Product> content = this.findDiscountProductsByLatestStartDate(latestStartDate, type, pageable);

        if (content.size() <= pageable.getPageSize()) {
            return new SliceImpl<>(content, pageable, false);
        }

        return new SliceImpl<>(
                content.stream()
                        .limit(pageable.getPageSize())
                        .collect(Collectors.toList()), pageable, true);
    }

    public LocalDateTime findLatestStartDate(ProductDiscount.Type type) {
        // 가장 최근 시작일 조회
        return queryFactory
                .select(productDiscount.startDate)
                .from(productDiscount)
                .where(productDiscount.type.eq(type))
                .orderBy(productDiscount.id.desc())
                .limit(1)
                .fetchOne();
    }

    public List<Product> findDiscountProductsByLatestStartDate(LocalDateTime latestStartDate, ProductDiscount.Type type, Pageable pageable) {
        return queryFactory
                .select(product)
                .from(productDiscount)
                .innerJoin(productDiscount.product, product)
                .where(product.status.eq(Product.Status.SHOW))
                .where(productDiscount.type.eq(type))
                .where(productDiscount.startDate.eq(latestStartDate))
                .limit(pageable.getPageSize() + 1)
                .offset(pageable.getOffset())
                .fetch();
    }
}
