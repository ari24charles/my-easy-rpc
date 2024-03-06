package com.ari24charles.easyrpc.service;

/**
 * 用于字符串加密的服务接口
 *
 * @author ari24charles
 */
public interface EncryptService {

    /**
     * 将字符串进行加密
     *
     * @param plaintext 字符串
     * @return 加密后的字符串
     */
    String encrypt(String plaintext);
}
