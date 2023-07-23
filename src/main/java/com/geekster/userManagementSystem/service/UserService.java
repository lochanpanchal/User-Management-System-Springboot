package com.geekster.userManagementSystem.service;

import com.geekster.userManagementSystem.models.User;
import com.geekster.userManagementSystem.reposatory.UserDaO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    @Autowired
    UserDaO userDaO;


    public List<User> getAllUser(){
        return userDaO.getUserList();
    }

    public String addUser(User user) {
        boolean addedStatus = userDaO.add(user);
        if(addedStatus) return " added successfully";
        return "User not added..!!";
    }

    public User userGetById(String userId) {
        List<User> userList = userDaO.getUserList();
        for(User user : userList){
            if(user.getUserId().equals(userId))
                return user;
        }
        return null;
    }

    public boolean updateUserById(String userId,String name) {
        List<User> userList = userDaO.getUserList();
        for(User user : userList){
            if(user.getUserId().equals(userId)){
                //remove original
                userDaO.remove(user);

                user.setName(name);

                userDaO.add(user);
                return true;
            }
        }
        return false;
    }

    public String removeUserByUserId(String userId) {
        String status = "";
        boolean uResponse = false;
        if(userId != null){
            List<User> userList = userDaO.getUserList();
            for(User user : userList){
                if(user.getUserId().equals(userId)) {
                    uResponse = userDaO.remove(user);
                    if (uResponse)
                        status = "UserId " + userId + " was deleted successfully";
                    else
                        status = "UserId " + userId + "  not found";
                    return status;
                }
            }
        }
        return "UserId "+userId +" not found..!!!";
    }
}
