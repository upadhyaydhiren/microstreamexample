package com.example.microstream.config;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;

import javax.cache.CacheManager;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import java.util.concurrent.TimeUnit;

public class CachingConfig implements JCacheManagerCustomizer {
    @Override
    public void customize(CacheManager cacheManager) {
        MutableConfiguration<Object, Object> mutableConfiguration = new MutableConfiguration<>()
                .setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(new Duration(TimeUnit.SECONDS, 10000)))
                .setStoreByValue(true)
                .setStatisticsEnabled(true);
        cacheManager.createCache("customer_cache", mutableConfiguration);
    }
}
