package com.game.qs.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by zun.wei on 2019/4/12 10:21.
 * Description: MD5 工具类
 */
@Slf4j
public class MD5Utils {

    /**
     * MD5方法
     *
     * @param text 明文
     * @param key  密钥
     * @return 密文
     */
    public static String md5(String text, String key) {
        //加密后的字符串
        String encodeStr = DigestUtils.md5Hex(text + key);
        log.info("MD5Utils encodeStr:{}", encodeStr);
        return encodeStr;
    }

    /**
     * MD5验证方法
     *
     * @param text 明文
     * @param key  密钥
     * @param md5  密文
     * @return true/false
     */
    public static boolean verify(String text, String key, String md5) {
        //根据传入的密钥进行验证
        String md5Text = md5(text, key);
        if (md5Text.equalsIgnoreCase(md5)) {
            log.info("MD5Utils MD5 verify pass");
            return true;
        }
        return false;
    }



}
