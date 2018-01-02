package com.depth.cms.content.metatype.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>@Title   </p>
 * <p>@Version 1.0.0 </p>
 * <p>@author chenjinxiu</p>
 * <p>@date 2017/11/22 11:37 </p>
 * <p>@Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */

public class BocUtils {
    private static Log log = LogFactory.getLog(BocUtils.class);
    private static String[] HanDigiStr = new String[]{"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    private static String[] HanDiviStr = new String[]{"", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "万", "拾", "佰", "仟"};

    public BocUtils() {
    }

    public static boolean isEmpty(Object pObj) {
        if(pObj == null) {
            return true;
        } else {
            if(pObj instanceof String) {
                if(((String)pObj).length() == 0) {
                    return true;
                }
            } else if(pObj instanceof Collection) {
                if(((Collection)pObj).size() == 0) {
                    return true;
                }
            } else if(pObj instanceof Map && ((Map)pObj).size() == 0) {
                return true;
            }

            return false;
        }
    }

    public static boolean isNotEmpty(Object pObj) {
        if(pObj == null) {
            return false;
        } else if(pObj == "") {
            return false;
        } else {
            if(pObj instanceof String) {
                if(((String)pObj).length() == 0) {
                    return false;
                }
            } else if(pObj instanceof Collection) {
                if(((Collection)pObj).size() == 0) {
                    return false;
                }
            } else if(pObj instanceof Map && ((Map)pObj).size() == 0) {
                return false;
            }

            return true;
        }
    }

    public static void copyPropBetweenBeans(Object pFromObj, Object pToObj) {
        if(pToObj != null) {
            try {
                BeanUtils.copyProperties(pToObj, pFromObj);
            } catch (Exception var3) {
                log.error("==开发人员请注意:==\n JavaBean之间的属性值拷贝发生错误啦!\n详细错误信息如下:");
                var3.printStackTrace();
            }
        }

    }

    public static String getFixedPersonIDCode(String personIDCode) throws Exception {
        if(personIDCode == null) {
            throw new Exception("输入的身份证号无效，请检查");
        } else if(personIDCode.length() == 18) {
            if(isIdentity(personIDCode)) {
                return personIDCode;
            } else {
                throw new Exception("输入的身份证号无效，请检查");
            }
        } else if(personIDCode.length() == 15) {
            return fixPersonIDCodeWithCheck(personIDCode);
        } else {
            throw new Exception("输入的身份证号无效，请检查");
        }
    }

    public static String fixPersonIDCodeWithCheck(String personIDCode) throws Exception {
        if(personIDCode != null && personIDCode.trim().length() == 15) {
            if(!isIdentity(personIDCode)) {
                throw new Exception("输入的身份证号无效，请检查");
            } else {
                return fixPersonIDCodeWithoutCheck(personIDCode);
            }
        } else {
            throw new Exception("输入的身份证号不足15位，请检查");
        }
    }

    public static String fixPersonIDCodeWithoutCheck(String personIDCode) throws Exception {
        if(personIDCode != null && personIDCode.trim().length() == 15) {
            String id17 = personIDCode.substring(0, 6) + "19" + personIDCode.substring(6, 15);
            char[] code = new char[]{'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
            int[] factor = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1};
            int[] idcd = new int[18];

            int lastCheckBit;
            for(lastCheckBit = 0; lastCheckBit < 17; ++lastCheckBit) {
                idcd[lastCheckBit] = Integer.parseInt(id17.substring(lastCheckBit, lastCheckBit + 1));
            }

            int sum = 0;

            for(lastCheckBit = 0; lastCheckBit < 17; ++lastCheckBit) {
                sum += idcd[lastCheckBit] * factor[lastCheckBit];
            }

            int remainder = sum % 11;
            String var8 = String.valueOf(code[remainder]);
            return id17 + var8;
        } else {
            throw new Exception("输入的身份证号不足15位，请检查");
        }
    }

    public static boolean isIdentity(String identity) {
        if(identity == null) {
            return false;
        } else if(identity.length() != 18 && identity.length() != 15) {
            return false;
        } else {
            String id15 = null;
            if(identity.length() == 18) {
                id15 = identity.substring(0, 6) + identity.substring(8, 17);
            } else {
                id15 = identity;
            }

            try {
                Long.parseLong(id15);
                String e = "19" + id15.substring(6, 12);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                sdf.parse(e);
                return identity.length() != 18 || fixPersonIDCodeWithoutCheck(id15).equals(identity);
            } catch (Exception var4) {
                return false;
            }
        }
    }

    public static Timestamp getBirthdayFromPersonIDCode(String identity) throws Exception {
        String id = getFixedPersonIDCode(identity);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        try {
            Timestamp e = new Timestamp(sdf.parse(id.substring(6, 14)).getTime());
            return e;
        } catch (ParseException var4) {
            throw new Exception("不是有效的身份证号，请检查");
        }
    }

    public static String getGenderFromPersonIDCode(String identity) throws Exception {
        String id = getFixedPersonIDCode(identity);
        char sex = id.charAt(16);
        return sex % 2 == 0?"2":"1";
    }

    private static String PositiveIntegerToHanStr(String NumStr) {
        String RMBStr = "";
        boolean lastzero = false;
        boolean hasvalue = false;
        int len = NumStr.length();
        if(len > 15) {
            return "数值过大!";
        } else {
            for(int i = len - 1; i >= 0; --i) {
                if(NumStr.charAt(len - i - 1) != 32) {
                    int n = NumStr.charAt(len - i - 1) - 48;
                    if(n < 0 || n > 9) {
                        return "输入含非数字字符!";
                    }

                    if(n != 0) {
                        if(lastzero) {
                            RMBStr = RMBStr + HanDigiStr[0];
                        }

                        if(n != 1 || i % 4 != 1 || i != len - 1) {
                            RMBStr = RMBStr + HanDigiStr[n];
                        }

                        RMBStr = RMBStr + HanDiviStr[i];
                        hasvalue = true;
                    } else if(i % 8 == 0 || i % 8 == 4 && hasvalue) {
                        RMBStr = RMBStr + HanDiviStr[i];
                    }

                    if(i % 8 == 0) {
                        hasvalue = false;
                    }

                    lastzero = n == 0 && i % 4 != 0;
                }
            }

            if(RMBStr.length() == 0) {
                return HanDigiStr[0];
            } else {
                return RMBStr;
            }
        }
    }

    public static String numToRMBStr(double val) {
        String SignStr = "";
        String TailStr = "";
        if(val < 0.0D) {
            val = -val;
            SignStr = "负";
        }

        if(val <= 1.0E14D && val >= -1.0E14D) {
            long temp = Math.round(val * 100.0D);
            long integer = temp / 100L;
            long fraction = temp % 100L;
            int jiao = (int)fraction / 10;
            int fen = (int)fraction % 10;
            if(jiao == 0 && fen == 0) {
                TailStr = "整";
            } else {
                TailStr = HanDigiStr[jiao];
                if(jiao != 0) {
                    TailStr = TailStr + "角";
                }

                if(integer == 0L && jiao == 0) {
                    TailStr = "";
                }

                if(fen != 0) {
                    TailStr = TailStr + HanDigiStr[fen] + "分";
                }
            }

            return SignStr + PositiveIntegerToHanStr(String.valueOf(integer)) + "元" + TailStr;
        } else {
            return "数值位数过大!";
        }
    }

    public static int getDaysInMonth(int year, int month) {
        return month != 1 && month != 3 && month != 5 && month != 7 && month != 8 && month != 10 && month != 12?(month != 4 && month != 6 && month != 9 && month != 11?((year % 4 != 0 || year % 100 == 0) && year % 400 != 0?28:29):30):31;
    }

    public static int getIntervalDays(Date startDate, Date endDate) {
        long startdate = startDate.getTime();
        long enddate = endDate.getTime();
        long interval = enddate - startdate;
        int intervalday = (int)(interval / 86400000L);
        return intervalday;
    }

    public static int getIntervalMonths(Date startDate, Date endDate) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);
        byte startDateM = 2;
        byte startDateY = 1;
        byte enddatem = 2;
        byte enddatey = 1;
        int interval = enddatey * 12 + enddatem - (startDateY * 12 + startDateM);
        return interval;
    }

    public static String getCurrentTime() {
        String returnStr = null;
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = new java.util.Date();
        returnStr = f.format(date);
        return returnStr;
    }

    public static int getCurrentDateAsInt() {
        String returnStr = null;
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        java.util.Date date = new java.util.Date();
        returnStr = f.format(date);
        return Integer.valueOf(returnStr).intValue();
    }

    public static BigDecimal getCurrentTimeAsNumber() {
        String returnStr = null;
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
        java.util.Date date = new java.util.Date();
        returnStr = f.format(date);
        return new BigDecimal(returnStr);
    }

    public static String getCurrentTime(String format) {
        String returnStr = null;
        SimpleDateFormat f = new SimpleDateFormat(format);
        java.util.Date date = new java.util.Date();
        returnStr = f.format(date);
        return returnStr;
    }

    public static String getCurDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = simpledateformat.format(calendar.getTime());
        return strDate;
    }

    public static String Date2String(java.util.Date date, String formatString) {
        if(isEmpty(date)) {
            return null;
        } else {
            SimpleDateFormat simpledateformat = new SimpleDateFormat(formatString);
            String strDate = simpledateformat.format(date);
            return strDate;
        }
    }

    public static String getCurDate(String format) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
        String strDate = simpledateformat.format(calendar.getTime());
        return strDate;
    }

    public static Timestamp getCurrentTimestamp() {
        Object obj = TypeCaseHelper.convert(getCurrentTime(), "Timestamp", "yyyy-MM-dd HH:mm:ss");
        return obj != null?(Timestamp)obj:null;
    }

    public static java.util.Date stringToDate(String strDate, String srcDateFormat, String dstDateFormat) {
        java.util.Date rtDate = null;
        java.util.Date tmpDate = (new SimpleDateFormat(srcDateFormat)).parse(strDate, new ParsePosition(0));
        String tmpString = null;
        if(tmpDate != null) {
            tmpString = (new SimpleDateFormat(dstDateFormat)).format(tmpDate);
        }

        if(tmpString != null) {
            rtDate = (new SimpleDateFormat(dstDateFormat)).parse(tmpString, new ParsePosition(0));
        }

        return rtDate;
    }

    public static String[] mergeStringArray(String[] a, String[] b) {
        if(a.length != 0 && !isEmpty(a)) {
            if(b.length != 0 && !isEmpty(b)) {
                String[] c = new String[a.length + b.length];

                int i;
                for(i = 0; i < a.length; ++i) {
                    c[i] = a[i];
                }

                for(i = 0; i < b.length; ++i) {
                    c[a.length + i] = b[i];
                }

                return c;
            } else {
                return a;
            }
        } else {
            return b;
        }
    }

    public static String getWeekDayByDate(String strdate) {
        String[] dayNames = new String[]{"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        java.util.Date date = new java.util.Date();

        try {
            date = sdfInput.parse(strdate);
        } catch (ParseException var6) {
            var6.printStackTrace();
        }

        calendar.setTime(date);
        int dayOfWeek = calendar.get(7) - 1;
        if(dayOfWeek < 0) {
            dayOfWeek = 0;
        }

        return dayNames[dayOfWeek];
    }

    public static String replace4JsOutput(String pStr) {
        pStr = pStr.replace("\r\n", "<br/>&nbsp;&nbsp;");
        pStr = pStr.replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
        pStr = pStr.replace(" ", "&nbsp;");
        return pStr;
    }

    public static String getPathFromClass(Class cls) {
        String path = null;
        if(cls == null) {
            throw new NullPointerException();
        } else {
            URL url = getClassLocationURL(cls);
            if(url != null) {
                path = url.getPath();
                if("jar".equalsIgnoreCase(url.getProtocol())) {
                    try {
                        path = (new URL(path)).getPath();
                    } catch (MalformedURLException var6) {
                        ;
                    }

                    int file = path.indexOf("!/");
                    if(file != -1) {
                        path = path.substring(0, file);
                    }
                }

                File file1 = new File(path);

                try {
                    path = file1.getCanonicalPath();
                } catch (IOException var5) {
                    var5.printStackTrace();
                }
            }

            return path;
        }
    }

    public static String getFullPathRelateClass(String relatedPath, Class cls) {
        String path = null;
        if(relatedPath == null) {
            throw new NullPointerException();
        } else {
            String clsPath = getPathFromClass(cls);
            File clsFile = new File(clsPath);
            String tempPath = clsFile.getParent() + File.separator + relatedPath;
            File file = new File(tempPath);

            try {
                path = file.getCanonicalPath();
            } catch (IOException var8) {
                var8.printStackTrace();
            }

            return path;
        }
    }

    public static boolean isMobile(String mobile) {
        String reg = "^1\\d{10}$";
        Pattern p = Pattern.compile(reg);
        Matcher match = p.matcher(mobile);
        boolean flag = match.matches();
        return flag;
    }

    private static URL getClassLocationURL(Class cls) {
        if(cls == null) {
            throw new IllegalArgumentException("null input: cls");
        } else {
            URL result = null;
            String clsAsResource = cls.getName().replace('.', '/').concat(".class");
            ProtectionDomain pd = cls.getProtectionDomain();
            if(pd != null) {
                CodeSource clsLoader = pd.getCodeSource();
                if(clsLoader != null) {
                    result = clsLoader.getLocation();
                }

                if(result != null && "file".equals(result.getProtocol())) {
                    try {
                        if(!result.toExternalForm().endsWith(".jar") && !result.toExternalForm().endsWith(".zip")) {
                            if((new File(result.getFile())).isDirectory()) {
                                result = new URL(result, clsAsResource);
                            }
                        } else {
                            result = new URL("jar:".concat(result.toExternalForm()).concat("!/").concat(clsAsResource));
                        }
                    } catch (MalformedURLException var6) {
                        ;
                    }
                }
            }

            if(result == null) {
                ClassLoader clsLoader1 = cls.getClassLoader();
                result = clsLoader1 != null?clsLoader1.getResource(clsAsResource):ClassLoader.getSystemResource(clsAsResource);
            }

            return result;
        }
    }

    public static BigDecimal getRandom(int start, int end) {
        return new BigDecimal((double)start + Math.random() * (double)end);
    }

    public static boolean writeString2File(String res, String filePath) {
        boolean flag = true;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        boolean buf;
        try {
            File e = new File(filePath);
            if(!e.getParentFile().exists()) {
                e.getParentFile().mkdirs();
            }

            bufferedReader = new BufferedReader(new StringReader(res));
            bufferedWriter = new BufferedWriter(new FileWriter(e));
            char[] buf1 = new char[1024];

            int e1;
            while((e1 = bufferedReader.read(buf1)) != -1) {
                bufferedWriter.write(buf1, 0, e1);
            }

            bufferedWriter.flush();
            bufferedReader.close();
            bufferedWriter.close();
            return flag;
        } catch (IOException var16) {
            var16.printStackTrace();
            flag = false;
            buf = flag;
        } finally {
            if(bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException var15) {
                    var15.printStackTrace();
                }
            }

        }

        return buf;
    }

    public static String readStringFromFile(File file, String encoding) {
        InputStreamReader reader = null;
        StringWriter writer = new StringWriter();

        Object n;
        try {
            if(encoding != null && !"".equals(encoding.trim())) {
                reader = new InputStreamReader(new FileInputStream(file));
            } else {
                reader = new InputStreamReader(new FileInputStream(file), encoding);
            }

            char[] e = new char[1024];
            boolean n1 = false;

            int n2;
            while(-1 != (n2 = reader.read(e))) {
                writer.write(e, 0, n2);
            }

            return writer != null?writer.toString():null;
        } catch (Exception var15) {
            var15.printStackTrace();
            n = null;
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException var14) {
                    var14.printStackTrace();
                }
            }

        }

        return (String)n;
    }

    public static String getGBK(String pString) {
        if(isEmpty(pString)) {
            return "";
        } else {
            try {
                pString = new String(pString.getBytes("ISO-8859-1"), "GBK");
            } catch (UnsupportedEncodingException var2) {
                var2.printStackTrace();
            }

            return pString;
        }
    }

    public static boolean isInteger(String value) {
        if(value == null) {
            return false;
        } else if(value.length() > 20) {
            return false;
        } else {
            try {
                Integer.parseInt(value);
                return true;
            } catch (NumberFormatException var2) {
                return false;
            }
        }
    }

    public static boolean isNumber(String value) {
        boolean result = value.matches("[0-9]+");
        return result;
    }

//    public static TreeMap<Integer, String> sortMapByKey(Map<Integer, String> oriMap) {
//        if(oriMap != null && !oriMap.isEmpty()) {
//            TreeMap sortedMap = new TreeMap(new Comparator() {
//                public int compare(Integer key1, Integer key2) {
//                    return key1.intValue() - key2.intValue();
//                }
//            });
//            sortedMap.putAll(oriMap);
//            return sortedMap;
//        } else {
//            return null;
//        }
//    }

    public static String getDecimalFormat(String s, String format) {
        DecimalFormat decimalFormat = new DecimalFormat(format);
        return decimalFormat.format(Double.parseDouble(s));
    }
}
