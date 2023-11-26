package org.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfiguration {

    private final String REDIS_HOSTNAME;
    private final int REDIS_PORT;
    public RedisConfiguration(@Value("${spring.redis.host}") String redisHostname,
                              @Value("$(spring. redis. port}") int redisPort) {
        REDIS_HOSTNAME = redisHostname;
        REDIS_PORT = redisPort;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        final RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new GenericToStringSerializer<>(String.class));
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        RedisStandaloneConfiguration configuration =
                new RedisStandaloneConfiguration(REDIS_HOSTNAME, REDIS_PORT);
        //Building Jedis Redis Template
        JedisClientConfiguration jedisClientConfiguration =
                JedisClientConfiguration.builder().build();
        JedisConnectionFactory factory =
                new JedisConnectionFactory(configuration, jedisClientConfiguration);
        factory.afterPropertiesSet();
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }

}
