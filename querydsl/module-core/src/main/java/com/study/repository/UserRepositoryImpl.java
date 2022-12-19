package com.study.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.domain.entity.User;
import com.study.repository.query.UserQueryRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.study.domain.entity.QUser.user;

public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserQueryRepository {

    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(JPAQueryFactory queryFactory) {
        super(User.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public List<User> search(User.OsPlatform osPlatform, Pageable pageable) {
        return queryFactory
                .selectFrom(user)
                .where(user.signupOsPlatform.eq(osPlatform))
                .orderBy(user.id.asc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();
    }
}
