package com.narainox.journalapplication.controller;

import com.narainox.journalapplication.dto.user.UserRequest;
import com.narainox.journalapplication.dto.user.UserResponse;
import com.narainox.journalapplication.service.UserService;
import com.narainox.journalapplication.utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PutMapping("/v1/updateUser")
    public ResponseEntity<Object> updateUser(@RequestBody UserRequest userRequest)
    {
        try{
            UserResponse response=userService.updateUser(userRequest);
            return ResponseHandler.generateResponse("user is updated",HttpStatus.OK,response);
        }
        catch (Exception ex)
        {
            return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }
    @DeleteMapping("/v1/deleteUser")
    public ResponseEntity<Object> deleteUser()
    {
        try{
            userService.deleteUser();
            return ResponseHandler.generateResponse("user is deleted",HttpStatus.OK,null);
        }
        catch (Exception ex)
        {
            return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }
}
