package com.ari24charles.easyrpc.proxy;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.ari24charles.easyrpc.model.RpcRequest;
import com.ari24charles.easyrpc.model.RpcResponse;
import com.ari24charles.easyrpc.serializer.JdkSerializer;
import com.ari24charles.easyrpc.serializer.Serializer;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 服务代理类 (JDK 动态代理实现)
 *
 * @author ari24charles
 */
public class ServiceProxy implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 构造 RPC 请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();
        try {
            Serializer serializer = new JdkSerializer();
            // 序列化
            byte[] bodyBytes = serializer.serialize(rpcRequest);
            // 发送请求到 Web 服务器
            // todo 服务发现机制
            try (HttpResponse httpResponse =
                         HttpRequest.post("http://localhost:8080")
                                 .body(bodyBytes)
                                 .execute()) {
                byte[] result = httpResponse.bodyBytes();
                // 反序列化
                RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
                return rpcResponse.getResult();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
