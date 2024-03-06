package com.ari24charles.easyrpc.server;

import io.vertx.core.Vertx;

/**
 * 基于 Vert.x 的 Web 服务器
 *
 * @author ari24charles
 */
public class VertxHttpServer implements HttpServer{

    @Override
    public void doStart(int port) {
        // 创建 Vert.x 实例
        Vertx vertx = Vertx.vertx();
        // 创建接收 HTTP 请求的 Web 服务器
        io.vertx.core.http.HttpServer server = vertx.createHttpServer();
        // 设置请求处理类
        server.requestHandler(new HttpServerHandler());
        // 启动服务器并监听指定端口
        server.listen(port, result -> {
            if (result.succeeded()) {
                System.out.println("Server is now listening on port " + port);
            } else {
                System.err.println("Failed to start server: " + result.cause());
            }
        });
    }
}
