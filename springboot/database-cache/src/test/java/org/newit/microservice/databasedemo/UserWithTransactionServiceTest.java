package org.newit.microservice.databasedemo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.newit.microservice.databasedemo.service.UserWithTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserWithTransactionServiceTest {

    @Autowired
    private UserWithTransactionService userWithTransactionService;

    @Test
    public void testTrans(){
        int insert = userWithTransactionService.insertUser();
        Assert.assertTrue(insert > 0);
    }

    @Test
    public void testTransUsingSpring() throws Exception {
        userWithTransactionService.insertWithTransaction();
    }

    @Test
    public void testCannotWorkTransaction() throws Exception{
        userWithTransactionService.cannotWorkTransaction();
    }
}
