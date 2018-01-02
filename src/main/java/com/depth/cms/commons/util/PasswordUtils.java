package com.depth.cms.commons.util;

/**
 * <p>@Title   </p>
 * <p>@Version 1.0.0 </p>
 * <p>@author chenjinxiu</p>
 * <p>@date 2017/11/23 15:34 </p>
 * <p>@Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */

public class PasswordUtils {
    public PasswordUtils() {
    }

    public static String encrypt(String pwd) {
        return Md5Util.encrypt(pwd);
    }

    public static String encrypts(String pwd) {
        return (new DESPlus("DGGERP962540ADMIN")).encrypt(pwd);
    }

    public static String decrypt(String pwd) {
        return (new DESPlus("DGGERP962540ADMIN")).decrypt(pwd);
    }
}

