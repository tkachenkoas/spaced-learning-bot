package com.atstudio.spacedlearningbot.cache;

import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.jsr107.Eh107Configuration;
import org.ehcache.jsr107.EhcacheCachingProvider;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.CacheManager;
import javax.cache.spi.CachingProvider;
import java.time.Duration;
import java.util.Arrays;

import static com.atstudio.spacedlearningbot.cache.CacheRegions.*;
import static java.time.Duration.ofSeconds;

@Configuration
@EnableCaching
public class CacheConfiguration {

    @Bean
    public JCacheCacheManager collectionsCacheManager() {
        CachingProvider provider = new EhcacheCachingProvider();
        CacheManager cacheManager = provider.getCacheManager();
        Arrays.stream(values()).forEach(region -> cacheManager.createCache(
                region.getCacheName(), createConfiguration(region.getDuration())
        ));
        return new JCacheCacheManager(cacheManager);
    }

    private javax.cache.configuration.Configuration<Object, Object> createConfiguration(Duration duration) {
        return Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder.newCacheConfigurationBuilder(
                        Object.class, Object.class, ResourcePoolsBuilder.heap(1000)
                ).withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(duration))
                        .build());
    }

}
