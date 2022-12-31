package com.study.v1.product.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.domain.entity.ProductDiscount;
import com.study.repository.query.ProductDiscountQueryRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.study.domain.entity.QProductDiscount.productDiscount;

@Repository
public class ProductDiscountRepositoryImpl extends QuerydslRepositorySupport implements ProductDiscountQueryRepository {

    private final JPAQueryFactory queryFactory;

    public ProductDiscountRepositoryImpl(JPAQueryFactory queryFactory) {
        super(ProductDiscount.class);
        this.queryFactory = queryFactory;
    }

    @Override
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

    @Override
    public Slice<ProductDiscount> findProductDiscountsByLatestStartDateAndType(ProductDiscount.Type type, Pageable pageable) {
        LocalDateTime latestStartDate = this.findLatestStartDate(type);
        List<ProductDiscount> productDiscounts = queryFactory
                .selectFrom(productDiscount)
                .where(productDiscount.validFlag.isTrue())
                .where(productDiscount.type.eq(type))
                .where(productDiscount.startDate.loe(latestStartDate))
                .where(productDiscount.endDate.goe(latestStartDate))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        boolean hasNext = !(productDiscounts.size() < pageable.getPageSize());

        return new SliceImpl<>(productDiscounts, pageable, hasNext);
    }
}
