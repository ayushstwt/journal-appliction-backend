package com.narainox.journalapplication.controller;

import com.narainox.journalapplication.dto.user.UserRequest;
import com.narainox.journalapplication.dto.user.UserResponse;
import com.narainox.journalapplication.service.UserService;
import com.narainox.journalapplication.utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody UserRequest createUserRequest)
    {
        try{
            UserResponse response=userService.createUser(createUserRequest);
            return ResponseHandler.generateResponse("user is created",HttpStatus.CREATED,response);
        }
        catch (Exception ex)
        {
            return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }
}
