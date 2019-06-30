package org.newit.microservice.databasedemo.config;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.collect.Lists;

@EnableCaching
@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<CaffeineCache> caches = Lists.newArrayList();
        CaffeineCache userCache = new CaffeineCache("userCache",
                          Caffeine.newBuilder().recordStats()
                                  .expireAfterWrite(10, TimeUnit.SECONDS)
                                  .maximumSize(100)
                                  .build());
        caches.add(userCache);
        cacheManager.setCaches(caches);
        return cacheManager;
    }
}
