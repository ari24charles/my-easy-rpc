package com.ari24charles.easyrpc.proxy;

import java.lang.reflect.Proxy;

/**
 * 服务代理工厂类
 *
 * @author ari24charles
 */
public class ServiceProxyFactory {

    /**
     * 根据服务接口获取代理对象
     *
     * @param clazz 服务接口
     * @param <T>   接口类型
     * @return
     */
    public static <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class[]{clazz},
                new ServiceProxy());
    }
}
