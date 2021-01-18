package com.rent.RentApp.configs.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

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

  @Bean
  public RedisTemplate<String, Object> redisTemplate() {
    final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
    redisTemplate.setConnectionFactory(jedisConnectionFactory());
    redisTemplate.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
    return redisTemplate;
  }

}
