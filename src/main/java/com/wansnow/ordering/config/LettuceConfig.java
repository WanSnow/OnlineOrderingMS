package com.wansnow.ordering.config;

import com.wansnow.ordering.config.info.RedisInfo;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class LettuceConfig {
    @Resource
    RedisInfo redisInfo;

    @Bean
    public RedisClient getRedisClient() {
        return RedisClient.create(redisInfo.getUri());
    }

    @Bean
    public StatefulRedisConnection<String, String> getStatefulRedisConnection(RedisClient redisClient){
        return redisClient.connect();
    }
}
