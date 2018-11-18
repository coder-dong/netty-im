package com.selflearn.nettyim.serializer.impl;

import com.alibaba.fastjson.JSONObject;
import com.selflearn.nettyim.serializer.Serializer;

/**
 * Created by coding-dong on 2018/11/1.
 */
public class JsonSerializer implements Serializer{
    @Override
    public byte getSerializerAlgorithm() {
        return 1;
    }

    @Override
    public <T> byte[] serializer(T t) {
        return JSONObject.toJSONBytes(t);
    }

    @Override
    public <T> T deSerializer(Class<T> tClass, byte[] bytes) {
        return JSONObject.parseObject(bytes, tClass);
    }
}
