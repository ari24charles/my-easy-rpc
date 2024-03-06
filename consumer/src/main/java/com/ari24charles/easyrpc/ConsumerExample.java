package com.ari24charles.easyrpc;

import com.ari24charles.easyrpc.proxy.ServiceProxyFactory;
import com.ari24charles.easyrpc.service.EncryptService;

/**
 * 消费者操作
 *
 * @author ari24charles
 */
public class ConsumerExample {

    public static void main(String[] args) {
        // 用接口获取动态代理对象
        EncryptService encryptService = ServiceProxyFactory.getProxy(EncryptService.class);
        // 调用远程方法
        String encrypt = encryptService.encrypt("hello,rpc");
        if (encrypt != null) {
            System.out.println("encrypt = " + encrypt);
        } else {
            System.out.println("调用失败");
        }
    }
}