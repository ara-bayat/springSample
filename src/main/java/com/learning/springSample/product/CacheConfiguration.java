package com.learning.springSample.product;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfiguration {
    @Bean
    public CacheManager cacheManager() {
        var cacheManager= new ConcurrentMapCacheManager();
        cacheManager.setCacheNames(List.of("products"));
        cacheManager.setAllowNullValues(false);
        return cacheManager;
    }

    @CacheEvict(value = "products",allEntries = true)
    @Scheduled(fixedDelay = 5000)
    public void evictCache() {
        System.out.println("evictCache");
    }


}
