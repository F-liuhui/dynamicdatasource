package com.example.demo.handler;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
@SuppressWarnings("hiding")
public class RedisSerializerHandler<Object> implements RedisSerializer<Object> {

	@Override
	public byte[] serialize(Object t) throws SerializationException {
		
		return JSON.toJSONBytes(t, SerializerFeature.EMPTY);
	}

	@Override
	public Object deserialize(byte[] bytes) throws SerializationException {
		//return JSON.parse(bytes, Feature.AUTO_CLOSE_JSON_CONTENT);
		return null;
	}
}
