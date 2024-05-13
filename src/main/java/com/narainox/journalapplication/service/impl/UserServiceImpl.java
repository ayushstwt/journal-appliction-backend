package com.narainox.journalapplication.service.impl;

import com.narainox.journalapplication.dto.user.UserRequest;
import com.narainox.journalapplication.dto.user.UserResponse;
import com.narainox.journalapplication.entity.User;
import com.narainox.journalapplication.exception.UsernameAlreadyExistsException;
import com.narainox.journalapplication.repository.UserRepository;
import com.narainox.journalapplication.service.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponse createUser(UserRequest createUserRequest) {
        boolean username = userRepository.existsByUsername(createUserRequest.getUsername());
        if (username)
        {
            throw new UsernameAlreadyExistsException("Username is already Exist");
        }
        User user = modelMapper.map(createUserRequest, User.class);
        user.setCreatedAt(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        user.setRole("USER");
        User save = userRepository.save(user);
        return modelMapper.map(save,UserResponse.class);
    }

    @Override
    public UserResponse updateUser(UserRequest userRequest) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(name).get();
        if (!userRepository.existsByUsername(userRequest.getUsername()))
        {
            user.setUsername(userRequest.getUsername());
        }
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        User save = userRepository.save(user);
        return modelMapper.map(save,UserResponse.class);
    }

    @Override
    public UserResponse createAdmin(UserRequest createUserRequest) {
        boolean username = userRepository.existsByUsername(createUserRequest.getUsername());
        if (username)
        {
            throw new UsernameAlreadyExistsException("Username is already Exist");
        }
        User user = modelMapper.map(createUserRequest, User.class);
        user.setCreatedAt(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        user.setRole("ADMIN");
        User save = userRepository.save(user);
        return modelMapper.map(save,UserResponse.class);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses=new ArrayList<>();
        for(User user:users)
        {
            userResponses.add(modelMapper.map(user,UserResponse.class));
        }
        return userResponses;
    }

    @Override
    @Transactional
    public void deleteUser() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        userRepository.deleteByUsername(name);
    }


}
