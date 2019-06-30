package org.newit.microservice.databasedemo.service;

import org.newit.microservice.databasedemo.dao.UserJdbcTmpl;
import org.newit.microservice.databasedemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserJdbcTmpl userJdbcTmpl;

    public boolean insertUser(User user){
        return userJdbcTmpl.insertUser(user) > 0;
    }

    public User getUserById(Long userId){
        return userJdbcTmpl.getUserById(userId);
    }
}
