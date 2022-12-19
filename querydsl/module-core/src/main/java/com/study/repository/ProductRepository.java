package com.study.repository;

import com.study.domain.entity.Product;
import com.study.repository.query.ProductQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>, ProductQueryRepository {
}
