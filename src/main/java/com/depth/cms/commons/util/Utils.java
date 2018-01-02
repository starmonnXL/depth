package com.depth.cms.commons.util;

/**
 * 系统数据字典常量表
 * <p>@Title CodeEnum</p>
 * <p>@Description com.dgg.web.menu.enums</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Administrator</p>
 * <p>@date 2016年11月24日</p>
 * <p>huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public class Utils {

    /**
     * 获取时间，输出格式为yyyy-MM-dd HH:mm:ss
     *
     * @param time 形如yyyyMMddHHmmss的时间字符串
     * @return 返回时间
     */
    public static String getTime(String time) {
        if (time == null || time.length() < 14) {
            throw new RuntimeException("传入时间有误");
        }
        String yyyy = time.substring(0, 4);
        String MM = time.substring(4, 6);
        String dd = time.substring(6, 8);
        String HH = time.substring(8, 10);
        String mm = time.substring(10, 12);
        String ss = time.substring(12, 14);
        time = yyyy + "-" + MM + "-" + dd + " " + HH + ":" + mm + ":" + ss;
        return time;
    }

    /**
     * 获取日期，输出格式为yyyy-MM-dd
     *
     * @param time 形如20170110171302的时间字符串
     * @return 时间的日期部分
     */
    public static String getDate(String time) {
        if (time == null || time.length() < 8) {
            throw new RuntimeException("传入时间有误");
        }
        char[] dateRs = new char[4 + 1 + 2 + 1 + 2];
        char[] date = time.toCharArray();
        for (int i = 0, n = 0; i < dateRs.length; i++, n++) {
            dateRs[i] = date[n];
            if (n == 3 || n == 5) {
                i++;
                dateRs[i] = '-';
            }
        }
        return String.valueOf(dateRs);
    }
}
