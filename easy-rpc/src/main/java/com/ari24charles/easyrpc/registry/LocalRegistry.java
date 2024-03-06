package com.ari24charles.easyrpc.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 本地服务注册中心
 *
 * @author ari24charles
 */
public class LocalRegistry {

    /**
     * 存储服务接口注册信息
     */
    private static final Map<String, Class<?>> serviceMap = new ConcurrentHashMap<>();

    /**
     * 注册服务
     *
     * @param serviceName 接口名
     * @param implClass   接口实现类
     */
    public static void register(String serviceName, Class<?> implClass) {
        serviceMap.put(serviceName, implClass);
    }

    /**
     * 根据接口名获取服务接口实现类
     *
     * @param serviceName 接口名
     * @return 服务接口实现类
     */
    public static Class<?> get(String serviceName) {
        return serviceMap.get(serviceName);
    }

    /**
     * 根据接口名移除服务接口实现类
     *
     * @param serviceName 接口名
     */
    public static void remove(String serviceName) {
        serviceMap.remove(serviceName);
    }
}
