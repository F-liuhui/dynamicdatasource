package com.example.demo.config;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
public class RedisConfig extends CachingConfigurerSupport {

	@Bean
	public CacheManager cacheManager(RedisConnectionFactory factory) {
		/**
		 * springboot1.x 和springboot2.x 得到RedisCacheConfiguration和RedisCacheManager的方式不一样，并且springboot2.x
		 * 使用了很多Java1.8的新特性，例如时间量Duration，接口默认方法等
		 */
        //Duration 为java8的新特性时间量
		//生成一个默认配置，通过config对象即可对缓存进行自定义配置
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();  
		//设置缓存的默认过期时间为1分钟，也是使用Duration设置 // 不缓存空值
	    config = config.entryTtl(Duration.ofMinutes(1)).disableCachingNullValues();

	    // 设置一个初始化的缓存策略set集合
	    Set<String> cacheNames =  new HashSet<>();
	    cacheNames.add("cacheStrategy1");//缓存策略1
	    cacheNames.add("cacheStrategy2");//缓存策略2

	    // 对每个缓存策略应用不同的配置
	    Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
	    configMap.put("cacheStrategy1", config);
	    //第二个缓存策略设置默认过期时间为两分钟，不缓存控制。
	    configMap.put("cacheStrategy1", config.entryTtl(Duration.ofMinutes(2)).disableCachingNullValues());
	    // 使用自定义的缓存配置初始化一个cacheManager
	    RedisCacheManager cacheManager = RedisCacheManager.builder(factory)     
	            .initialCacheNames(cacheNames)  //注意这两句的调用顺序，一定要先调用该方法设置初始化的缓存名，再初始化相关的配置
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
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuffer buff=new StringBuffer();
				buff.append(target.getClass().getName());
				buff.append("."+method.getName());
				for(Object o:params) {
					buff.append("."+o.toString());
				}
				return buff.toString();
			}
			
		};
	}

	@Override
	public CacheErrorHandler errorHandler() {
		return null;
	}
}
