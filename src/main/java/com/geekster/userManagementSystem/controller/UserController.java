package com.geekster.userManagementSystem.controller;

import com.geekster.userManagementSystem.models.User;
import com.geekster.userManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService UserService;

    @PostMapping("/user")
    public String postUser(@RequestBody User user){
        return UserService.addUser(user);
    }

    @GetMapping("/user/{userId}")
    public User getUserById(@PathVariable String userId){
        return UserService.userGetById(userId);
    }

    @GetMapping("/user")
    public List<User> getUser(){
        return UserService.getAllUser();
    }

    @PutMapping(value = "/user/{userId}/{name}")
    public String updateUser(@PathVariable String userId,@PathVariable String name){
        return UserService.updateUserById(userId,name)? "Updated successfully":"Not found user with this id..!!";
    }
    @DeleteMapping(value = "/user/{userId}")
    public String deleteUserByUserId(@PathVariable String userId){
        return UserService.removeUserByUserId(userId);
    }


}
