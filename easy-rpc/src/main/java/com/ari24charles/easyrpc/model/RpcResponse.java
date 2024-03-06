package com.ari24charles.easyrpc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * RPC 响应体
 *
 * @author ari24charles
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RpcResponse implements Serializable {

    /**
     * 响应数据
     */
    private Object result;

    /**
     * 响应数据的类型
     */
    private Class<?> resultType;

    /**
     * 响应信息
     */
    private String msg;

    /**
     * 异常信息
     */
    private Exception exception;
}
