package com.example.demo.config;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configuration
//启用缓存
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {


	//获得springboot封装的redis缓存管理类 RedisCacheManager


	@Autowired //注入redis链接工厂
	private RedisConnectionFactory factory;


	@Override
	public CacheManager cacheManager() {
		/**
		 * springboot1.x 和springboot2.x 得到RedisCacheConfiguration和RedisCacheManager的方式不一样，并且springboot2.x
		 * 使用了很多Java1.8的新特性，例如时间量Duration，接口默认方法等
		 */
        //Duration 为java8的新特性时间量
		//生成一个默认配置，通过config对象即可对缓存进行自定义配置（该类是springboot封装的用于处理Redis配置的一个工具类,可以直接拿来用）
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();  
		//设置缓存的默认过期时间，也是使用Duration设置 ，不缓存空值entryTtl方法会返回一个新的RedisCacheConfiguration对象

	    // 设置两个redisCache(采用不同的缓存策略)，采用默认的RedisCacheConfiguration初始化缓存时用。
	    //Set<String> cacheNames =  new HashSet<>();
	    //cacheNames.add("cacheStrategy1");//缓存策略1
	    //cacheNames.add("cacheStrategy2");//缓存策略2


	    // 对每个缓存应用不同的策略
	    Map<String, RedisCacheConfiguration> configMap = new LinkedHashMap<>();
	    ////设置缓存的默认过期时间，也是使用Duration设置 ，不缓存空值entryTtl方法会返回一个新的RedisCacheConfiguration对象
	    configMap.put("cacheStrategy1", config.entryTtl(Duration.ofMinutes(1)).disableCachingNullValues());
	    //第二个缓存策略设置默认过期时间为两分钟，不缓存空值。
	    configMap.put("cacheStrategy2", config.entryTtl(Duration.ofMinutes(2)).disableCachingNullValues());


	    // 初始化一个RedisCacheManager
	    RedisCacheManager cacheManager = RedisCacheManager.builder(factory)
		        // 在该方法中会调用withInitialCacheConfigurations方法，会用一个默认的RedisCacheConfiguration去初始化缓存配置)，在初始化缓存的配置
	            // .initialCacheNames(cacheNames)
				//这里采用自定义的RedisCacheConfiguration去初始化缓存
	            .withInitialCacheConfigurations(configMap)
	            .build();
	    return cacheManager;
	}
    
	@Override
	public CacheResolver cacheResolver() {
		return null;
	}

	@Override
	public KeyGenerator keyGenerator() {
		return (target, method, params) -> {
			StringBuffer buff=new StringBuffer();
			buff.append(target.getClass().getName());
			buff.append("."+method.getName());
			for(Object o:params) {
				buff.append("."+o.toString());
			}
			return buff.toString();
		};
	}

	@Override
	public CacheErrorHandler errorHandler() {
		return null;
	}
}
