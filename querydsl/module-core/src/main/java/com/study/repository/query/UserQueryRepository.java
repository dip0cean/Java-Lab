package com.study.repository.query;

import com.study.domain.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserQueryRepository {
    List<User> search(User.OsPlatform osPlatform, Pageable pageable);
}
