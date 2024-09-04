package com.uwdp.module.biz.utils;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AseUtil {
    static String key = "htpc-mim-service";
    public static String encryptBase64(String content){
        try{
            byte[] byteKey = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue(),
                    key.getBytes()).getEncoded();
            SymmetricCrypto aes = SecureUtil.aes(byteKey);
            // 加密
            return aes.encryptBase64(content);
        }catch (Exception e){
            log.error(" 加密异常:{}",e.getMessage());
        }
        return null;
    }

    public static String decryptStr(String encryptString){
        try{
            byte[] byteKey = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue(),
                    key.getBytes()).getEncoded();
            SymmetricCrypto aes = SecureUtil.aes(byteKey);
            //解密
            return aes.decryptStr(encryptString);
        }catch (Exception e){
            log.error(" 解密异常:{}",e.getMessage());
        }
        return null;
    }
}
