package org.newit.microservice.databasedemo.dao;

import java.util.UUID;

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
public class UserJdbcTmplTest {

    @Autowired
    private UserJdbcTmpl userJdbcTmpl;

    @Test
    public void testInsert(){
        User user =new User();
        user.setName("test name");
        userJdbcTmpl.insertUser(user);
    }

    @Test
    public void testInsertReturnId(){
        User user = new User();
        user.setName("return id");
        userJdbcTmpl.insertUserReturnId(user);
        System.out.println(user.getId());
    }

    @Test
    public void testSelectById(){
        String randomName = UUID.randomUUID().toString();
        User user = new User();
        user.setName(randomName);
        userJdbcTmpl.insertUserReturnId(user);
        Assert.assertTrue(user.getId() > 0);

        User userFromDB = userJdbcTmpl.getUserById(user.getId());
        Assert.assertEquals(user.getName(), userFromDB.getName());
    }
}
