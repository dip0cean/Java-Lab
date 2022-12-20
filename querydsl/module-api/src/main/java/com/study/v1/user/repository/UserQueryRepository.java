package com.study.v1.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.domain.entity.User;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class UserQueryRepository extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public UserQueryRepository(JPAQueryFactory queryFactory) {
        super(User.class);
        this.queryFactory = queryFactory;
    }
}
