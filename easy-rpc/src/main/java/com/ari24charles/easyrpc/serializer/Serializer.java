package com.ari24charles.easyrpc.serializer;

import java.io.IOException;

/**
 * 序列化器接口
 *
 * @author ari24charles
 */
public interface Serializer {

    /**
     * 将对象序列化成字节数组
     *
     * @param object 对象
     * @param <T>    对象类型
     * @return 字节数组
     * @throws IOException
     */
    <T> byte[] serialize(T object) throws IOException;

    /**
     * 将字节数组反序列化成对象
     *
     * @param bytes 字节数组
     * @param type  对象类型
     * @param <T>   对象类型
     * @return 对象
     * @throws IOException
     */
    <T> T deserialize(byte[] bytes, Class<T> type) throws IOException;
}
