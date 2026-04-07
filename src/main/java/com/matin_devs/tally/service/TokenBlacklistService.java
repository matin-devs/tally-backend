package com.matin_devs.tally.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class TokenBlacklistService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void blacklist(String token, Date expiration) {
        long ttl = expiration.getTime() - System.currentTimeMillis();
        redisTemplate.opsForValue().set(token, "blacklisted", ttl, TimeUnit.MILLISECONDS);
    }

    public boolean isBlacklisted(String token) {
        return redisTemplate.hasKey(token);
    }
}
