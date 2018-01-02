package com.depth.cms.commons.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * <p>@Title   </p>
 * <p>@Version 1.0.0 </p>
 * <p>@author chenjinxiu</p>
 * <p>@date 2017/11/20 17:36 </p>
 * <p>@Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */

public class StringUtilParent {

    public static final String EMPTY = "";
    public static final int INDEX_NOT_FOUND = -1;
    private static final int PAD_LIMIT = 8192;
    private static final Pattern WHITESPACE_BLOCK = Pattern.compile("\\s+");
    private static boolean sunAvailable = false;
    private static Method sunDecomposeMethod = null;
    private static final Pattern sunPattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
    private static boolean java6Available = false;
    private static Method java6NormalizeMethod = null;
    private static Object java6NormalizerFormNFD = null;
    private static final Pattern java6Pattern;

    public StringUtilParent() {
    }

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence cs) {
        return !StringUtil.isEmpty(cs);
    }

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if(cs != null && cs.toString().toLowerCase() != "null" && (strLen = cs.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if(!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static boolean isNotBlank(CharSequence cs) {
        return !StringUtil.isBlank(cs);
    }

    public static String trim(String str) {
        return str == null?null:str.trim();
    }

    public static String trimToNull(String str) {
        String ts = trim(str);
        return isEmpty(ts)?null:ts;
    }

    public static String trimToEmpty(String str) {
        return str == null?"":str.trim();
    }

    public static String strip(String str) {
        return strip(str, (String)null);
    }

    public static String stripToNull(String str) {
        if(str == null) {
            return null;
        } else {
            str = strip(str, (String)null);
            return str.length() == 0?null:str;
        }
    }

    public static String stripToEmpty(String str) {
        return str == null?"":strip(str, (String)null);
    }

    public static String strip(String str, String stripChars) {
        if(isEmpty(str)) {
            return str;
        } else {
            str = stripStart(str, stripChars);
            return stripEnd(str, stripChars);
        }
    }

    public static String stripStart(String str, String stripChars) {
        int strLen;
        if(str != null && (strLen = str.length()) != 0) {
            int start = 0;
            if(stripChars == null) {
                while(start != strLen && Character.isWhitespace(str.charAt(start))) {
                    ++start;
                }
            } else {
                if(stripChars.length() == 0) {
                    return str;
                }

                while(start != strLen && stripChars.indexOf(str.charAt(start)) != -1) {
                    ++start;
                }
            }

            return str.substring(start);
        } else {
            return str;
        }
    }

    public static String stripEnd(String str, String stripChars) {
        int end;
        if(str != null && (end = str.length()) != 0) {
            if(stripChars == null) {
                while(end != 0 && Character.isWhitespace(str.charAt(end - 1))) {
                    --end;
                }
            } else {
                if(stripChars.length() == 0) {
                    return str;
                }

                while(end != 0 && stripChars.indexOf(str.charAt(end - 1)) != -1) {
                    --end;
                }
            }

            return str.substring(0, end);
        } else {
            return str;
        }
    }

    public static String[] stripAll(String... strs) {
        return stripAll(strs, (String)null);
    }

    public static String[] stripAll(String[] strs, String stripChars) {
        int strsLen;
        if(strs != null && (strsLen = strs.length) != 0) {
            String[] newArr = new String[strsLen];

            for(int i = 0; i < strsLen; ++i) {
                newArr[i] = strip(strs[i], stripChars);
            }

            return newArr;
        } else {
            return strs;
        }
    }

    public static String stripAccents(String input) {
        if(input == null) {
            return null;
        } else {
            try {
                String se = null;
                if(java6Available) {
                    se = removeAccentsJava6(input);
                } else {
                    if(!sunAvailable) {
                        throw new UnsupportedOperationException("The stripAccents(CharSequence) method requires at least Java 1.6 or a Sun JVM");
                    }

                    se = removeAccentsSUN(input);
                }

                return se;
            } catch (IllegalArgumentException var2) {
                throw new RuntimeException("IllegalArgumentException occurred", var2);
            } catch (IllegalAccessException var3) {
                throw new RuntimeException("IllegalAccessException occurred", var3);
            } catch (InvocationTargetException var4) {
                throw new RuntimeException("InvocationTargetException occurred", var4);
            } catch (SecurityException var5) {
                throw new RuntimeException("SecurityException occurred", var5);
            }
        }
    }

    private static String removeAccentsJava6(CharSequence text) throws IllegalAccessException, InvocationTargetException {
        if(java6Available && java6NormalizerFormNFD != null) {
            String result = (String)java6NormalizeMethod.invoke((Object)null, new Object[]{text, java6NormalizerFormNFD});
            result = java6Pattern.matcher(result).replaceAll("");
            return result;
        } else {
            throw new IllegalStateException("java.text.Normalizer is not available");
        }
    }

    private static String removeAccentsSUN(CharSequence text) throws IllegalAccessException, InvocationTargetException {
        if(!sunAvailable) {
            throw new IllegalStateException("sun.text.Normalizer is not available");
        } else {
            String result = (String)sunDecomposeMethod.invoke((Object)null, new Object[]{text, Boolean.FALSE, Integer.valueOf(0)});
            result = sunPattern.matcher(result).replaceAll("");
            return result;
        }
    }

    public static boolean equals(CharSequence cs1, CharSequence cs2) {
        return cs1 == null?cs2 == null:cs1.equals(cs2);
    }

    public static String toSBC(String input) {
        char[] c = input.toCharArray();

        for(int i = 0; i < c.length; ++i) {
            if(c[i] == 32) {
                c[i] = 12288;
            } else if(c[i] < 127) {
                c[i] += 'ﻠ';
            }
        }

        return new String(c);
    }

    public static String toDBC(String input) {
        char[] c = input.toCharArray();

        for(int i = 0; i < c.length; ++i) {
            if(c[i] == 12288) {
                c[i] = 32;
            } else if(c[i] > '\uff00' && c[i] < '｟') {
                c[i] -= 'ﻠ';
            }
        }

        return new String(c);
    }

    public static String fillBitsWithPlaceholder(String placeholder, String str, int bitNum) {
        if(str == null) {
            throw new IllegalArgumentException("str must not be null");
        } else if(placeholder != null && bitNum > 0) {
            if(str.length() >= bitNum) {
                return str;
            } else {
                StringBuffer buffer = new StringBuffer();

                for(int index = str.length(); index < bitNum; ++index) {
                    buffer.append(placeholder);
                }

                buffer.append(str);
                return buffer.toString();
            }
        } else {
            return str;
        }
    }

    public static String spliceIntegerToString(Integer addend, Integer summand) {
        if(addend != null && summand != null) {
            return addend + String.valueOf(summand);
        } else {
            throw new IllegalArgumentException("addend and summand arguments must not be null");
        }
    }

    public static String defaultIfNull(String str) {
        return str != null?str:"";
    }

    public static String rightPadWithBytes(String str, int size, char padChar, String encoding) {
        return padWithBytes(str, size, padChar, encoding, false);
    }

    public static String leftPadWithBytes(String str, int size, char padChar, String encoding) {
        return padWithBytes(str, size, padChar, encoding, true);
    }

    private static String padWithBytes(String str, int size, char padChar, String encoding, boolean isLeft) {
        if(str == null) {
            return null;
        } else {
            int strLen;
            try {
                strLen = str.getBytes(encoding).length;
            } catch (UnsupportedEncodingException var9) {
                throw new RuntimeException("UnsupportedEncoding:" + encoding, var9);
            }

            int pads = size - strLen;
            if(pads <= 0) {
                return str;
            } else {
                char[] padChars = new char[pads];

                for(int i = 0; i < padChars.length; ++i) {
                    padChars[i] = padChar;
                }

                return isLeft?new String(padChars) + str:str + new String(padChars);
            }
        }
    }

    public static String replaceStringByChar(int beginLength, int endLength, char replaceChar, String srcStr) {
        if(isBlank(srcStr)) {
            return null;
        } else {
            if(beginLength < 0) {
                beginLength = 0;
            }

            if(endLength < 0) {
                endLength = 0;
            }

            StringBuffer returnStr = new StringBuffer();
            int srcLength = srcStr.length();
            int replaceLength = srcLength - (beginLength + endLength);
            int i;
            if(srcLength >= beginLength && srcLength >= endLength && replaceLength >= 0) {
                returnStr.append(srcStr.substring(0, beginLength));

                for(i = 0; i < replaceLength; ++i) {
                    returnStr.append(replaceChar);
                }

                returnStr.append(srcStr.substring(srcStr.length() - endLength));
                return returnStr.toString();
            } else {
                for(i = 0; i < srcLength; ++i) {
                    returnStr.append(replaceChar);
                }

                return returnStr.toString();
            }
        }
    }

    static {
        java6Pattern = sunPattern;

        Class e;
        try {
            e = Thread.currentThread().getContextClassLoader().loadClass("java.text.Normalizer$Form");
            java6NormalizerFormNFD = e.getField("NFD").get((Object)null);
            Class normalizerClass = Thread.currentThread().getContextClassLoader().loadClass("java.text.Normalizer");
            java6NormalizeMethod = normalizerClass.getMethod("normalize", new Class[]{CharSequence.class, e});
            java6Available = true;
        } catch (ClassNotFoundException var4) {
            java6Available = false;
        } catch (NoSuchFieldException var5) {
            java6Available = false;
        } catch (IllegalAccessException var6) {
            java6Available = false;
        } catch (NoSuchMethodException var7) {
            java6Available = false;
        }

        try {
            e = Thread.currentThread().getContextClassLoader().loadClass("sun.text.Normalizer");
            sunDecomposeMethod = e.getMethod("decompose", new Class[]{String.class, Boolean.TYPE, Integer.TYPE});
            sunAvailable = true;
        } catch (ClassNotFoundException var2) {
            sunAvailable = false;
        } catch (NoSuchMethodException var3) {
            sunAvailable = false;
        }

    }
}
