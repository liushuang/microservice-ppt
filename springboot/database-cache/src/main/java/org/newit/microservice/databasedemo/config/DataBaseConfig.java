package org.newit.microservice.databasedemo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "org.newit.microservice.databasedemo.dao")
public class DataBaseConfig {

}
