package com.study.repository;

import com.study.domain.entity.ProductDiscount;
import com.study.repository.query.ProductDiscountQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDiscountRepository extends JpaRepository<ProductDiscount, String>, QuerydslPredicateExecutor<ProductDiscount>, ProductDiscountQueryRepository {
}
