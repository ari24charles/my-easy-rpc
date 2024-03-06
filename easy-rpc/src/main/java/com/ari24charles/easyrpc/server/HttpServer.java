package com.ari24charles.easyrpc.server;

/**
 * Web 服务器接口
 *
 * @author ari24charles
 */
public interface HttpServer {

    /**
     * 启动 Web 服务器
     *
     * @param port 端口号
     */
    void doStart(int port);
}
