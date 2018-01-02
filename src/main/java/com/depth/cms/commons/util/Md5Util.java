package com.depth.cms.commons.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>@Title   </p>
 * <p>@Version 1.0.0 </p>
 * <p>@author chenjinxiu</p>
 * <p>@date 2017/11/23 15:34 </p>
 * <p>@Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */

public class Md5Util {
    public Md5Util() {
    }

    public static String encrypt(String str) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException var5) {
            return str;
        } catch (UnsupportedEncodingException var6) {
            return str;
        }

        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();

        for(int i = 0; i < byteArray.length; ++i) {
            if(Integer.toHexString(255 & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(255 & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(255 & byteArray[i]));
            }
        }

        return md5StrBuff.toString();
    }

    public static boolean validate(String plaintext, String ciphertext) {
        return ciphertext.equalsIgnoreCase(encrypt(plaintext));
    }

    public static boolean validateEquals(String plaintext, String ciphertext) {
        return ciphertext.equalsIgnoreCase(plaintext);
    }
}

