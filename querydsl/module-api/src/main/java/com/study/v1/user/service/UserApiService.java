package com.study.v1.user.service;

import com.study.domain.entity.User;
import com.study.repository.UserRepository;
import com.study.service.UserCoreService;
import com.study.v1.user.repository.UserQueryRepository;
import org.springframework.stereotype.Service;

@Service
public class UserApiService extends UserCoreService {

    private final UserQueryRepository queryRepository;

    public UserApiService(UserRepository repository,
                          UserQueryRepository queryRepository) {
        super(repository);
        this.queryRepository = queryRepository;
    }

    public User findUser(String id) {
        return this.repository
                .findById(Long.parseLong(id))
                .orElse(User.builder().build());
    }
}
