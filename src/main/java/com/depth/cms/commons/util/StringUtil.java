package com.depth.cms.commons.util;

import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>@Title   </p>
 * <p>@Version 1.0.0 </p>
 * <p>@author chenjinxiu</p>
 * <p>@date 2017/11/20 17:35 </p>
 * <p>@Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */

public class StringUtil  extends StringUtilParent {

    public StringUtil() {
    }

    public static boolean isNotNull(String str) {
        return str != null && !"".equals(str.trim());
    }

    public static boolean isNotNull(Object obj) {
        return obj != null && obj.toString() != null && !"".equals(obj.toString().trim());
    }

    public static boolean isEmpty(String str) {
        return isEmpty(str, true);
    }

    public static boolean isEmpty(String str, boolean trim) {
        return str == null?true:"".equals(str.trim());
    }

    public static String[] parseToArray(String str, String delim) {
        ArrayList arr = new ArrayList();
        StringTokenizer st = new StringTokenizer(str, delim);

        while(st.hasMoreTokens()) {
            arr.add(st.nextToken());
        }

        String[] ret = new String[arr.size()];

        for(int i = 0; i < arr.size(); ++i) {
            ret[i] = (String)arr.get(i);
        }

        return ret;
    }

    public static String replace(String str, String old, String rep) {
        if(str != null && old != null && rep != null) {
            int index = str.indexOf(old);
            if(index >= 0 && !"".equals(old)) {
                StringBuffer strBuf;
                for(strBuf = new StringBuffer(str); index >= 0; index = strBuf.toString().indexOf(old)) {
                    strBuf.delete(index, index + old.length());
                    strBuf.insert(index, rep);
                }

                return strBuf.toString();
            } else {
                return str;
            }
        } else {
            return "";
        }
    }

    public static Number stringToNumber(String str) throws ParseException {
        if(str != null && !"".equals(str)) {
            DecimalFormatSymbols dfs = new DecimalFormatSymbols();
            dfs.setDecimalSeparator('.');
            dfs.setGroupingSeparator(',');
            dfs.setMonetaryDecimalSeparator('.');
            DecimalFormat df = new DecimalFormat("###,###,###,###.##", dfs);
            return df.parse(str);
        } else {
            return null;
        }
    }

    public static String getExtensionName(String filename) {
        if(filename != null && filename.length() > 0) {
            int dot = filename.lastIndexOf(46);
            if(dot > -1 && dot < filename.length() - 1) {
                return filename.substring(dot + 1);
            }
        }

        return filename;
    }

    public static String replacement(String target, String replacement, String value) {
        return target != null?target.replace(replacement, value):null;
    }

    public static boolean isNumeric(String str) {
        if(str != null && str.length() != 0) {
            for(int i = 0; i < str.length(); ++i) {
                if(!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public static String convDateToString(Date date) {
        Long time = Long.valueOf((new Date()).getTime() - date.getTime());
        Long min = Long.valueOf(time.longValue() / 1000L / 60L);
        return min.longValue() < 5L?"刚刚":(min.longValue() >= 5L && min.longValue() < 60L?min + "分钟之前":(min.longValue() >= 60L && min.longValue() < 1440L?min.longValue() / 60L + "小时之前":(min.longValue() >= 1440L && min.longValue() < 10080L?min.longValue() / 60L / 24L + "天之前":(min.longValue() >= 10080L && min.longValue() < 40320L?min.longValue() / 60L / 24L / 7L + "周之前":(min.longValue() >= 40320L && min.longValue() < 525600L?min.longValue() / 60L / 24L / 7L / 4L + "月之前":(min.longValue() >= 525600L?min.longValue() / 60L / 24L / 365L + "年之前":null))))));
    }

    public static String getCurrdate(String formatStr) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
        String mDateTime = formatter.format(cal.getTime());
        return mDateTime;
    }

    public static double getDoubleByObj(Object value) {
        return value == null?0.0D:Double.valueOf(String.valueOf(value)).doubleValue();
    }

    public static float getFloatByObj(Object value) {
        return value == null?0.0F:Float.valueOf(String.valueOf(value)).floatValue();
    }

    public static Integer getIntegerByObj(Object value) {
        return value == null?Integer.valueOf(0):Integer.valueOf(String.valueOf(value));
    }

    public static String parse(String str) {
        return str.replaceAll("\n", "").replaceAll("chr(13)", "").replaceAll(" ", "");
    }

    public static Integer[] Str2Integers(String value) {
        if(null != value && StringUtils.hasText(value)) {
            String[] values = value.split(",");
            Integer[] v = new Integer[values.length];

            for(int i = 0; i < values.length; ++i) {
                v[i] = Integer.valueOf(Integer.parseInt(values[i]));
            }

            return v;
        } else {
            return null;
        }
    }

    public static String[] Str2Strings(String value) {
        if(null != value && StringUtils.hasText(value)) {
            String[] values = value.split(",");
            String[] v = new String[values.length];

            for(int i = 0; i < values.length; ++i) {
                v[i] = values[i];
            }

            return v;
        } else {
            return null;
        }
    }

    public static int strFormateInt(Object obj) {
        return isNotNull(obj)?("是".equals(obj)?1:0):0;
    }

    public static String getUUID() {
        return (UUID.randomUUID() + "").replaceAll("-", "");
    }

    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = new StringBuffer();
        byte[] bGBK = cnStr.getBytes();

        for(int i = 0; i < bGBK.length; ++i) {
            strBuf.append(Integer.toHexString(bGBK[i] & 255));
        }

        return strBuf.toString();
    }

    public static String getNextCode(String initCode, int length, int ind) {
        Integer temp = Integer.valueOf(Integer.parseInt(initCode));
        temp = Integer.valueOf(temp.intValue() + ind);
        String tempCode = temp.toString();
        int tempLen = 0;
        if(tempCode.length() < length) {
            tempLen = length - tempCode.length();
        }

        for(int i = 0; i < tempLen; ++i) {
            tempCode = "0" + tempCode;
        }

        return tempCode;
    }

    public static int switchNumber(String str) {
        char c = str.charAt(0);
        byte temp = 0;
        switch(c) {
            case '〇':
            case '零':
                temp = 0;
                break;
            case '一':
                temp = 1;
                break;
            case '七':
                temp = 7;
                break;
            case '三':
                temp = 3;
                break;
            case '九':
                temp = 9;
                break;
            case '二':
                temp = 2;
                break;
            case '五':
                temp = 5;
                break;
            case '八':
                temp = 8;
                break;
            case '六':
                temp = 6;
                break;
            case '十':
                temp = 10;
                break;
            case '四':
                temp = 4;
        }

        return temp;
    }

    public static int cnNumToInt(String s) {
        byte result = 0;
        int yi = 1;
        short wan = 1;
        short ge = 1;
        char c = s.charAt(0);
        int temp = 0;
        switch(c) {
            case '〇':
            case '零':
                temp = 0;
                break;
            case '一':
                temp = 1 * ge * wan * yi;
                ge = 1;
                break;
            case '七':
                temp = 7 * ge * wan * yi;
                ge = 1;
                break;
            case '万':
                wan = 10000;
                ge = 1;
                break;
            case '三':
                temp = 3 * ge * wan * yi;
                ge = 1;
                break;
            case '九':
                temp = 9 * ge * wan * yi;
                ge = 1;
                break;
            case '二':
                temp = 2 * ge * wan * yi;
                ge = 1;
                break;
            case '五':
                temp = 5 * ge * wan * yi;
                ge = 1;
                break;
            case '亿':
                yi = 100000000;
                wan = 1;
                ge = 1;
                break;
            case '八':
                temp = 8 * ge * wan * yi;
                ge = 1;
                break;
            case '六':
                temp = 6 * ge * wan * yi;
                ge = 1;
                break;
            case '十':
                ge = 10;
                break;
            case '千':
                ge = 1000;
                break;
            case '四':
                temp = 4 * ge * wan * yi;
                ge = 1;
                break;
            case '百':
                ge = 100;
                break;
            default:
                return -1;
        }

        int result1 = result + temp;
        if(ge > 1) {
            result1 += 1 * ge * wan * yi;
        }

        return result1;
    }

    public static String geneStrAry(String str, String splits) {
        if(isEmpty(str)) {
            return "";
        } else {
            String[] ary = str.split(splits);
            StringBuffer sb = new StringBuffer("");

            for(int i = 0; i < ary.length; ++i) {
                sb.append("\'");
                sb.append(ary[i]);
                sb.append("\'");
                if(i < ary.length - 1) {
                    sb.append(",");
                }
            }

            return sb.toString();
        }
    }

    public static boolean equals(String str1, String str2) {
        return str1 == null?false:(str2 == null?true:str1.equals(str2));
    }

    public static boolean equalsIgnoreCase(String str1, String str2) {
        return str1 == null?false:(str2 == null?true:str1.equalsIgnoreCase(str2));
    }

    public static String decimalFormat(Object obj) {
        if(null == obj) {
            return "";
        } else {
            DecimalFormat df = new DecimalFormat("0.00");
            return df.format(obj);
        }
    }

    public static String decimalFormat(Object obj, String format) {
        if(null == obj) {
            return "";
        } else {
            DecimalFormat df = new DecimalFormat(format);
            return df.format(obj);
        }
    }
}
