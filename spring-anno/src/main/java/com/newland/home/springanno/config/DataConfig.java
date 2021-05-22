package com.newland.home.springanno.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @author ${linfengpeng}
 * @Title: DataConfig
 * @ProjectName Springboot
 * @Description: @Profile 环境标识，默认：default
 *  第一种方式切换： 1、-Dspring.profiles.active=test
 *                2、
 * @date 2020/9/29 10:03
 */
@Configuration
public class DataConfig {

    @Profile("test")
    @Bean
    public DataSource dataSourceTest() throws PropertyVetoException {
        ComboPooledDataSource data = new ComboPooledDataSource();
        data.setUser("user");
        data.setPassword("admin");
        data.setJdbcUrl("jdbc:mysql://localhost:2271/test");
        data.setDriverClass("com.mysql.jdbc.Driver");
        return data;
    }
    @Profile("dev")
    @Bean
    public DataSource dataSourceDev() throws PropertyVetoException {
        ComboPooledDataSource data = new ComboPooledDataSource();
        data.setUser("user");
        data.setPassword("admin");
        data.setJdbcUrl("jdbc:mysql://localhost:2271/test");
        data.setDriverClass("com.mysql.jdbc.Driver");
        return data;
    }
    @Profile("Prob")
    @Bean
    public DataSource dataSourceProb() throws PropertyVetoException {
        ComboPooledDataSource data = new ComboPooledDataSource();
        data.setUser("user");
        data.setPassword("admin");
        data.setJdbcUrl("jdbc:mysql://localhost:2271/test");
        data.setDriverClass("com.mysql.jdbc.Driver");
        return data;
    }


}
