package org.newit.microservice.databasedemo.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.newit.microservice.databasedemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert(){
        User user = new User();
        user.setName("test from mybatis");
        userMapper.insert(user);
    }

    @Test
    public void testSelect(){
        User user = new User();
        user.setName("test from mybatis2");
        userMapper.insert(user);

        User userFromDB = userMapper.selectById(user.getId());
        Assert.assertNotNull(userFromDB);
        Assert.assertEquals(user.getName(), userFromDB.getName());
    }
}
