package com.study.repository.service;

import com.study.domain.User;
import com.study.repository.UserRepository;
import com.study.repository.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserRepositoryImpl userRepositoryImpl;

    public User findById(String id) {
        return userRepository.findById(id).orElse(new User());
    }

    public List<User> searchByOsPlatform(User.OsPlatform osPlatform, Pageable pageable) {
        return userRepositoryImpl.search(osPlatform, pageable);
    }
}
