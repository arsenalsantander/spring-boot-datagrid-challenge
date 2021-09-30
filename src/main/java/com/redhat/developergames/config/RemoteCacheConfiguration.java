package com.redhat.developergames.config;

import com.redhat.developergames.model.Weather;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RemoteCacheConfiguration {

    private final RemoteCacheManager cacheManager;

    @Autowired
    private Weather weather;

    @Autowired
    public RemoteCacheConfiguration(RemoteCacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public void testeCache(RemoteCacheManager cacheManager) {
        cacheManager.getCache("testCache").put("testKey", "testValue");
        System.out.println("Received value from cache: " + cacheManager.getCache("testCache").get("testKey"));
    }

    public void addValues(RemoteCacheManager cacheManager) {
        cacheManager.getCache("testCache").put("Weather","{" + "temperature=" + weather.getTemperature() + ", condition=" + weather.getCondition() + ", city='" + weather.getCity() + '\'' + '}');
    }


}
