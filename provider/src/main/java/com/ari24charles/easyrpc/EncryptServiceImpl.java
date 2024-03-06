package com.ari24charles.easyrpc;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.ari24charles.easyrpc.service.EncryptService;

/**
 * MD5 加密字符串的服务接口实现类
 *
 * @author ari24charles
 */
public class EncryptServiceImpl implements EncryptService {

    @Override
    public String encrypt(String plaintext) {
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        return md5.digestHex(plaintext);
    }
}
