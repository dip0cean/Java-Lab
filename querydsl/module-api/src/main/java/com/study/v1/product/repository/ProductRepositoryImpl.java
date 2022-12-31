package com.study.v1.product.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.domain.entity.Product;
import com.study.domain.entity.ProductDiscount;
import com.study.domain.entity.ProductOption;
import com.study.repository.query.ProductQueryRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.study.domain.entity.QProduct.product;
import static com.study.domain.entity.QProductDiscount.productDiscount;
import static com.study.domain.entity.QProductOption.productOption;

@Repository
public class ProductRepositoryImpl extends QuerydslRepositorySupport implements ProductQueryRepository {

    private final JPAQueryFactory queryFactory;

    public ProductRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Product.class);
        this.queryFactory = queryFactory;
    }

    public Slice<Product> findDiscountProducts_1(ProductDiscount.Type type, Pageable pageable) {
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

    @Override
    public Slice<Product> findDiscountProducts_2(ProductDiscount.Type type, Pageable pageable) {
        // 가장 최근 시작일 조회
        LocalDateTime latestStartDate = this.findLatestStartDate(type);

        // 제품 조회
        List<Product> products = this.findDiscountProductsByLatestStartDate(latestStartDate, type, pageable);

        // 제품 ID 리스트
        List<String> productIds = products.stream().map(Product::getId).distinct().toList();

        // 제품 별 제품 옵션 리스트
        Map<String, List<ProductOption>> productOptions = this.findProductOptionsByProductIds(productIds);

        // 제품 옵션 ID 리스트
        List<String> productOptionIds = productOptions.values().stream().flatMap(Collection::stream).map(ProductOption::getId).distinct().toList();

        // 제품 옵션 별 핫딜 / 타임세일
        Map<String, List<ProductDiscount>> productDiscounts = this.findProductDiscountByProductOptionIds(productOptionIds);

        productOptions.values()
                .stream()
                .flatMap(Collection::stream)
                .filter(productOption -> productDiscounts.get(productOption.getId()) != null)
                .forEach(productOption -> productOption.setProductDiscounts(productDiscounts.get(productOption.getId())));

        products.stream().filter(product -> productOptions.get(product.getId()) != null).forEach(product -> product.setProductOptions(productOptions.get(product.getId())));

        if (products.size() <= pageable.getPageSize()) {
            return new SliceImpl<>(products, pageable, false);
        }

        return new SliceImpl<>(
                products.stream()
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

    public Map<String, List<ProductOption>> findProductOptionsByProductIds(Collection<String> productIds) {
        return queryFactory
                .selectFrom(productOption)
                .where(productOption.productId.in(productIds))
                .where(productOption.status.eq(ProductOption.Status.SHOW))
                .fetch()
                .stream()
                .collect(Collectors.groupingBy(ProductOption::getProductId));
    }

    public Map<String, List<ProductDiscount>> findProductDiscountByProductOptionIds(Collection<String> productOptionIds) {
        LocalDateTime now = LocalDateTime.now();
        return queryFactory
                .selectFrom(productDiscount)
                .where(productDiscount.productOptionId.in(productOptionIds))
                .where(productDiscount.startDate.loe(now))
                .where(productDiscount.endDate.goe(now))
                .where(productDiscount.validFlag.isTrue())
                .fetch()
                .stream()
                .collect(Collectors.groupingBy(ProductDiscount::getProductOptionId));
    }
}
