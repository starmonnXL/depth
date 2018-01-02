package com.depth.cms.commons.util;

import com.sun.crypto.provider.SunJCE;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.Security;

/**
 * <p>@Title   </p>
 * <p>@Version 1.0.0 </p>
 * <p>@author chenjinxiu</p>
 * <p>@date 2017/11/23 15:34 </p>
 * <p>@Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */

public class DESPlus {
    private static String strDefaultKey = "national";
    private Cipher encryptCipher;
    private Cipher decryptCipher;

    public static String byteArr2HexStr(byte[] arrB) throws Exception {
        int iLen = arrB.length;
        StringBuffer sb = new StringBuffer(iLen * 2);

        for(int i = 0; i < iLen; ++i) {
            int intTmp;
            for(intTmp = arrB[i]; intTmp < 0; intTmp += 256) {
                ;
            }

            if(intTmp < 16) {
                sb.append("0");
            }

            sb.append(Integer.toString(intTmp, 16));
        }

        return sb.toString();
    }

    public static byte[] hexStr2ByteArr(String strIn) throws Exception {
        byte[] arrB = strIn.getBytes("UTF-8");
        int iLen = arrB.length;
        byte[] arrOut = new byte[iLen / 2];

        for(int i = 0; i < iLen; i += 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte)Integer.parseInt(strTmp, 16);
        }

        return arrOut;
    }

    public DESPlus() throws Exception {
        this(strDefaultKey);
    }

    public DESPlus(String strKey) {
        this.encryptCipher = null;
        this.decryptCipher = null;

        try {
            Security.addProvider(new SunJCE());
            Key e = this.getKey(strKey.getBytes());
            this.encryptCipher = Cipher.getInstance("DES");
            this.encryptCipher.init(1, e);
            this.decryptCipher = Cipher.getInstance("DES");
            this.decryptCipher.init(2, e);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public byte[] encrypt(byte[] arrB) {
        try {
            return this.encryptCipher.doFinal(arrB);
        } catch (IllegalBlockSizeException var3) {
            var3.printStackTrace();
            return null;
        } catch (BadPaddingException var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public String encrypt(String strIn) {
        try {
            return byteArr2HexStr(this.encrypt(strIn.getBytes("UTF-8")));
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public byte[] decrypt(byte[] arrB) throws Exception {
        return this.decryptCipher.doFinal(arrB);
    }

    public String decrypt(String strIn) {
        try {
            return new String(this.decrypt(hexStr2ByteArr(strIn)), "UTF-8");
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }

    private Key getKey(byte[] arrBTmp) throws Exception {
        byte[] arrB = new byte[8];

        for(int key = 0; key < arrBTmp.length && key < arrB.length; ++key) {
            arrB[key] = arrBTmp[key];
        }

        SecretKeySpec var4 = new SecretKeySpec(arrB, "DES");
        return var4;
    }
}

