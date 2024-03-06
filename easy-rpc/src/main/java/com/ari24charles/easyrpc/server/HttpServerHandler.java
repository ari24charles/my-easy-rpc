package com.ari24charles.easyrpc.server;

import com.ari24charles.easyrpc.model.RpcRequest;
import com.ari24charles.easyrpc.model.RpcResponse;
import com.ari24charles.easyrpc.registry.LocalRegistry;
import com.ari24charles.easyrpc.serializer.JdkSerializer;
import com.ari24charles.easyrpc.serializer.Serializer;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 请求处理器
 *
 * @author ari24charles
 */
public class HttpServerHandler implements Handler<HttpServerRequest> {

    @Override
    public void handle(HttpServerRequest request) {
        // 指定序列化器
        Serializer serializer = new JdkSerializer();
        // 记录日志
        System.out.println("Received request: " + request.method() + " " + request.uri());
        // 处理请求
        request.bodyHandler(body -> {
            byte[] bytes = body.getBytes();
            RpcRequest rpcRequest = null;
            try {
                rpcRequest = serializer.deserialize(bytes, RpcRequest.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 构造 RPC 响应
            RpcResponse rpcResponse = new RpcResponse();
            // 如果请求为 null，直接返回
            if (rpcRequest == null) {
                rpcResponse.setMsg("rpcRequest is null");
                doResponse(request, rpcResponse, serializer);
                return;
            }
            try {
                // todo 负载均衡
                // 获取要调用的服务实现类
                Class<?> implClass = LocalRegistry.get(rpcRequest.getServiceName());
                Method method = implClass.getMethod(rpcRequest.getMethodName(), rpcRequest.getParameterTypes());
                Object result = method.invoke(implClass.newInstance(), rpcRequest.getArgs());
                // 封装返回结果
                rpcResponse.setResult(result);
                rpcResponse.setResultType(method.getReturnType());
                rpcResponse.setMsg("ok");
            } catch (Exception e) {
                // todo 容错机制
                e.printStackTrace();
                rpcResponse.setMsg(e.getMessage());
                rpcResponse.setException(e);
            }
            // 响应
            doResponse(request, rpcResponse, serializer);
        });
    }

    void doResponse(HttpServerRequest request, RpcResponse rpcResponse, Serializer serializer) {
        HttpServerResponse httpServerResponse = request.response()
                .putHeader("content-type", "application/json");
        try {
            // 序列化
            byte[] serialized = serializer.serialize(rpcResponse);
            httpServerResponse.end(Buffer.buffer(serialized));
        } catch (IOException e) {
            e.printStackTrace();
            httpServerResponse.end(Buffer.buffer());
        }
    }
}
