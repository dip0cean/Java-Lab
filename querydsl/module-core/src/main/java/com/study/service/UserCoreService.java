package com.study.service;

import com.study.domain.entity.User;
import com.study.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserCoreService {

    protected final UserRepository repository;

    public User findUser(long id) {
        return repository.findById(id).orElse(User.builder().build());
    }
}
