package com.narainox.journalapplication.controller;

import com.narainox.journalapplication.dto.user.UserRequest;
import com.narainox.journalapplication.dto.user.UserResponse;
import com.narainox.journalapplication.repository.UserRepository;
import com.narainox.journalapplication.service.UserService;
import com.narainox.journalapplication.utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @PostMapping("/createAdmin")
    public ResponseEntity<Object> createAdmin(@RequestBody UserRequest userRequest)
    {
        try{
            UserResponse response=userService.createAdmin(userRequest);
            return ResponseHandler.generateResponse("admin is created",HttpStatus.CREATED,response);
        }
        catch (Exception ex)
        {
            return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }
    @GetMapping("/getUser")
    public ResponseEntity<Object> getAllUser()
    {
        try{
            List<UserResponse> users = userService.getAllUsers();
            return ResponseHandler.generateResponse(null,HttpStatus.CREATED,users);
        }
        catch (Exception ex)
        {
            return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }
}
