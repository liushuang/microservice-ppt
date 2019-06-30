package org.newit.microservice.ebusiness.repository;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.newit.microservice.ebusiness.dao.UserMapper;
import org.newit.microservice.ebusiness.model.User;
import org.newit.microservice.ebusiness.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private UserMapper userMapper;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void insert(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insert(user);
    }

    public User getUserByName(String name) {
        UserExample example = new UserExample();
        example.createCriteria().andNameEqualTo(name);
        List<User> users = userMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(users)){
            return users.get(0);
        }
        return null;
    }

    public User getUserById(Long userId) {
        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(userId);
        List<User> users = userMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(users)){
            return users.get(0);
        }
        return null;
    }
}
