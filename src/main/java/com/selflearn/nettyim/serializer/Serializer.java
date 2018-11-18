package com.selflearn.nettyim.serializer;

import com.selflearn.nettyim.serializer.impl.JsonSerializer;

/**
 * Created by coding-dong on 2018/11/1.
 */
public interface Serializer {

    Serializer DEFAULT = new JsonSerializer();

    byte getSerializerAlgorithm();

    <T> byte[] serializer(T t);

    <T> T deSerializer(Class<T> tClass, byte[] bytes);
}
