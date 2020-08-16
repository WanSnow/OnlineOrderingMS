package com.wansnow.ordering.config.info;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:mysql.properties")
public class JdbcInfo {

    @Value("${mysql.url}")
    private String url;

    @Value("${mysql.driver}")
    private String driver;

    @Value("${mysql.username}")
    private String username;

    @Value("${mysql.password}")
    private String password;

    public String getUrl() {
        return url;
    }

    public String getDriver() {
        return driver;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
