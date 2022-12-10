package com.study.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.domain.User;
import com.study.repository.query.UserQueryRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.study.domain.QUser.user;

public class UserRepositoryImpl implements UserQueryRepository {

    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(JPAQueryFactory queryFactory) {
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
