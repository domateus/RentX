package com.rent.RentApp.configs.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
public class JedisConfig {

  @Bean
  public JedisConnectionFactory jedisConnectionFactory() {
    JedisConnectionFactory jedisConnectionFactory = null;

    try {
      jedisConnectionFactory = new JedisConnectionFactory(new RedisStandaloneConfiguration());
      jedisConnectionFactory.getPoolConfig().setMaxTotal(10);
      jedisConnectionFactory.getPoolConfig().setMaxIdle(10);
    } catch (RedisConnectionFailureException e) {
      e.getMessage();
    }

    return jedisConnectionFactory;
  }

}
