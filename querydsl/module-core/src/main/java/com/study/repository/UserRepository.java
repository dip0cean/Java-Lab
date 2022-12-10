package com.study.repository;

import com.study.domain.User;
import com.study.repository.query.UserQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>, UserQueryRepository {
}
