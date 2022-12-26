package com.study.kotlin.service.user;

import com.study.kotlin.domain.user.User;
import com.study.kotlin.domain.user.UserRepository;
import com.study.kotlin.dto.user.request.UserCreateRequest;
import com.study.kotlin.dto.user.request.UserUpdateRequest;
import com.study.kotlin.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveUser(UserCreateRequest request) {
        User newUser = new User(request.getName(), request.getAge());
        userRepository.save(newUser);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUserName(UserUpdateRequest request) {
        User user = userRepository.findById(request.getId()).orElseThrow(IllegalArgumentException::new);
        user.updateName(request.getName());
    }

    @Transactional
    public void deleteUser(String name) {
        User user = userRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
        userRepository.delete(user);
    }

}
