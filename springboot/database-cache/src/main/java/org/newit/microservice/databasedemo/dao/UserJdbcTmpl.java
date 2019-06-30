package org.newit.microservice.databasedemo.dao;

import java.util.Date;
import java.util.Map;

import org.newit.microservice.databasedemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UserJdbcTmpl {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public int insertUser(User user) {
        return jdbcTemplate.update("insert into user(name, created_time) values(?,NOW())", user.getName());
    }

    public int insertUserReturnId(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("userName", user.getName());

        int insert = namedParameterJdbcTemplate.update("insert into user(name, created_time) values(:userName, NOW())", parameterSource, keyHolder, new String[]{"id"});

        long userId =  keyHolder.getKey().longValue();
        user.setId(userId);
        return insert;
    }

    public User getUserById(Long id) {
        Map<String, Object> resultMap = jdbcTemplate.queryForMap(
                "select id, name, created_time from user where id = " + id);
        User user = new User();
        user.setId((Long) resultMap.get("id"));
        user.setName((String) resultMap.get("name"));
        user.setCreatedTime(((Date) resultMap.get("created_time")));
        return user;
    }
}
