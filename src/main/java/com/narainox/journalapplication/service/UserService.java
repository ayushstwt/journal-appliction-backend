package com.narainox.journalapplication.service;

import com.narainox.journalapplication.dto.user.UserRequest;
import com.narainox.journalapplication.dto.user.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest createUserRequest);
    UserResponse updateUser(UserRequest createUserRequest);
    UserResponse createAdmin(UserRequest userRequest);
    List<UserResponse>  getAllUsers();
    void deleteUser();
}
