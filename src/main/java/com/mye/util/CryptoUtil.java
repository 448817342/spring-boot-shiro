package com.mye.util;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 加密工具
 * @author zb
 * @date 2019-04-02 11:18
 **/
public class CryptoUtil {

    private static final RandomNumberGenerator RANDOM_NUMBER_GENERATOR = new SecureRandomNumberGenerator();
    /**
     * 加密方式
     */
    public static final String ALGORITHM_NAME = "MD5";
    /**
     * 加密次数
     */
    public static final int HASH_ITERATION = 2;

    /**
     * 自动生成盐值
     */
    public static String generateSalt() {
        return RANDOM_NUMBER_GENERATOR.nextBytes().toHex();
    }

    /**
     * 获取盐值加密后的密码
     * @param password 密码
     * @param salt 盐值
     * @return 加密后的数据
     */
    public static String generateMD5(String password, String salt){
        //盐：为了即使相同的密码不同的盐加密后的结果也不同
        ByteSource byteSalt = ByteSource.Util.bytes(salt);
        SimpleHash result = new SimpleHash(ALGORITHM_NAME, password, byteSalt, HASH_ITERATION);
        return result.toString();
    }

    public static void main(String[] args) {
        String salt = generateSalt();
        System.out.println(salt);
        System.out.println(generateMD5("admin", salt));
    }

}
