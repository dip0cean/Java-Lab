package com.study.service;

import com.study.domain.entity.User;
import com.study.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findById(String id) {
        return userRepository.findById(id).orElse(new User());
    }

    public List<User> searchByOsPlatform(User.OsPlatform osPlatform, Pageable pageable) {
        return userRepository.search(osPlatform, pageable);
    }
}
