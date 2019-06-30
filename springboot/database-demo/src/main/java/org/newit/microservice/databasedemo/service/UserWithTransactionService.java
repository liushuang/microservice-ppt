package org.newit.microservice.databasedemo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserWithTransactionService {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertUser(){
        Connection conn = null;
        int result = 0;
        try{
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            PreparedStatement ps = conn.prepareStatement(
                    "insert into user(name, created_time) values(?, now())");
            ps.setString(1, "test transaction user name2");
            result = ps.executeUpdate();
            conn.commit();
        }catch (Exception e){
            if(conn != null){
                try{
                    conn.rollback();
                }catch (SQLException sel){
                    sel.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally {
            try {
                if(conn != null && !conn.isClosed()){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Transactional
    public int insertWithTransaction() throws Exception {
        jdbcTemplate.update("insert into user(name, created_time) values(?,NOW())", "user 1");
        if(System.currentTimeMillis() > 0) {
            throw new RuntimeException();
        }
        jdbcTemplate.update("insert into user(name, created_time) values(?,NOW())", "user 2");
        return 1;
    }

    public void cannotWorkTransaction() throws Exception {
        insertWithTransaction();
    }
}
