package com.wansnow.ordering.config;

import com.wansnow.ordering.utils.SnowFlake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilsConfig {

    @Bean(name = "shop")
    public SnowFlake getShopIdGenerator(){
        return new SnowFlake(4, 5);
    }

    @Bean(name = "dish")
    public SnowFlake getDishIdGenerator(){
        return new SnowFlake(4, 6);
    }

    @Bean(name = "order")
    public SnowFlake getOrderGenerator(){
        return new SnowFlake(4, 7);
    }
}
