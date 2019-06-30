package org.newit.microservice.databasedemo.service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.checkerframework.checker.units.qual.A;
import org.newit.microservice.databasedemo.dao.UserJdbcTmpl;
import org.newit.microservice.databasedemo.dao.UserMapper;
import org.newit.microservice.databasedemo.model.User;
import org.newit.microservice.databasedemo.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    LoadingCache<Long, User> userCache = CacheBuilder.newBuilder()
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .build(new CacheLoader<Long, User>() {
                @Override
                public User load(Long userId) throws Exception {
                    return getUserById(userId);
                }
            });


    public User getUserWitchCache(Long userId){
        try {
            return userCache.get(userId);
        } catch (ExecutionException e) {
            // ignore
        }
        return getUserById(userId);
    }

    @Autowired
    private UserJdbcTmpl userJdbcTmpl;

    public boolean insertUser(User user){
        return userJdbcTmpl.insertUser(user) > 0;
    }

    public User getUserById(Long userId){
        return userJdbcTmpl.getUserById(userId);
    }

    @Cacheable(value = "userCache", sync = true)
    public User getUserWithCaffeineCache(Long userId){
        return userJdbcTmpl.getUserById(userId);
    }

    public List<User> getUserList() {
        UserExample example = new UserExample();
        return userMapper.selectByExample(example);
    }
}
