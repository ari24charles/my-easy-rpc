package com.ari24charles.easyrpc;

import com.ari24charles.easyrpc.registry.LocalRegistry;
import com.ari24charles.easyrpc.server.HttpServer;
import com.ari24charles.easyrpc.server.VertxHttpServer;
import com.ari24charles.easyrpc.service.EncryptService;

/**
 * 服务提供者操作 (包括开启 RPC 框架中的 Web 服务器)
 *
 * @author ari24charles
 */
public class ProviderExample {

    public static void main(String[] args) {
        // 注册服务
        LocalRegistry.register(EncryptService.class.getName(), EncryptServiceImpl.class);

        // 开启 Web 服务器
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(8080);
    }
}