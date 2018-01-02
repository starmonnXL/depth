package com.depth.cms.content.metatype.util;

import com.depth.cms.content.exception.TypeCastException;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * <p>@Title   </p>
 * <p>@Version 1.0.0 </p>
 * <p>@author chenjinxiu</p>
 * <p>@date 2017/11/22 11:29 </p>
 * <p>@Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */

public class TypeCaseHelper {
    public TypeCaseHelper() {
    }

    public static Object convert(Object obj, String type, String format) throws TypeCastException {
        Locale locale = new Locale("zh", "CN", "");
        if(obj == null) {
            return null;
        } else if(obj.getClass().getName().equals(type)) {
            return obj;
        } else if(!"Object".equals(type) && !"java.lang.Object".equals(type)) {
            String fromType = null;
            SimpleDateFormat sdf;
            BigDecimal retBig;
            int iscale;
            if(obj instanceof String) {
                fromType = "String";
                String bol8 = (String)obj;
                if(!"String".equals(type) && !"java.lang.String".equals(type)) {
                    if(bol8.length() == 0) {
                        return null;
                    } else if(!"Boolean".equals(type) && !"java.lang.Boolean".equals(type)) {
                        Number sdf3;
                        if(!"Double".equals(type) && !"java.lang.Double".equals(type)) {
                            if(!"BigDecimal".equals(type) && !"java.math.BigDecimal".equals(type)) {
                                if(!"Float".equals(type) && !"java.lang.Float".equals(type)) {
                                    Number retBig2;
                                    NumberFormat sdf5;
                                    if(!"Long".equals(type) && !"java.lang.Long".equals(type)) {
                                        if(!"Integer".equals(type) && !"java.lang.Integer".equals(type)) {
                                            DateFormat retBig3;
                                            Date iscale1;
                                            Date retBig4;
                                            if(!"Date".equals(type) && !"java.sql.Date".equals(type)) {
                                                if(!"Timestamp".equals(type) && !"java.sql.Timestamp".equals(type)) {
                                                    throw new TypeCastException("Conversion from " + fromType + " to " + type + " not currently supported");
                                                } else {
                                                    if(bol8.length() == 10) {
                                                        bol8 = bol8 + " 00:00:00";
                                                    }

                                                    if(format != null && format.length() != 0) {
                                                        try {
                                                            sdf = new SimpleDateFormat(format);
                                                            retBig4 = sdf.parse(bol8);
                                                            return new Timestamp(retBig4.getTime());
                                                        } catch (ParseException var12) {
                                                            throw new TypeCastException("Could not convert " + bol8 + " to " + type + ": ", var12);
                                                        }
                                                    } else {
                                                        try {
                                                            return Timestamp.valueOf(bol8);
                                                        } catch (Exception var13) {
                                                            try {
                                                                retBig = null;
                                                                if(locale != null) {
                                                                    retBig3 = DateFormat.getDateTimeInstance(3, 3, locale);
                                                                } else {
                                                                    retBig3 = DateFormat.getDateTimeInstance(3, 3);
                                                                }

                                                                iscale1 = retBig3.parse(bol8);
                                                                return new Timestamp(iscale1.getTime());
                                                            } catch (ParseException var11) {
                                                                throw new TypeCastException("Could not convert " + bol8 + " to " + type + ": ", var13);
                                                            }
                                                        }
                                                    }
                                                }
                                            } else if(format != null && format.length() != 0) {
                                                try {
                                                    sdf = new SimpleDateFormat(format);
                                                    retBig4 = sdf.parse(bol8);
                                                    return new java.sql.Date(retBig4.getTime());
                                                } catch (ParseException var14) {
                                                    throw new TypeCastException("Could not convert " + bol8 + " to " + type + ": ", var14);
                                                }
                                            } else {
                                                try {
                                                    return java.sql.Date.valueOf(bol8);
                                                } catch (Exception var15) {
                                                    try {
                                                        retBig = null;
                                                        if(locale != null) {
                                                            retBig3 = DateFormat.getDateInstance(3, locale);
                                                        } else {
                                                            retBig3 = DateFormat.getDateInstance(3);
                                                        }

                                                        iscale1 = retBig3.parse(bol8);
                                                        return new java.sql.Date(iscale1.getTime());
                                                    } catch (ParseException var10) {
                                                        throw new TypeCastException("Could not convert " + bol8 + " to " + type + ": ", var15);
                                                    }
                                                }
                                            }
                                        } else {
                                            try {
                                                sdf5 = getNf(locale);
                                                sdf5.setMaximumFractionDigits(0);
                                                retBig2 = sdf5.parse(bol8);
                                                return new Integer(retBig2.intValue());
                                            } catch (ParseException var16) {
                                                throw new TypeCastException("Could not convert " + bol8 + " to " + type + ": ", var16);
                                            }
                                        }
                                    } else {
                                        try {
                                            sdf5 = getNf(locale);
                                            sdf5.setMaximumFractionDigits(0);
                                            retBig2 = sdf5.parse(bol8);
                                            return new Long(retBig2.longValue());
                                        } catch (ParseException var17) {
                                            throw new TypeCastException("Could not convert " + bol8 + " to " + type + ": ", var17);
                                        }
                                    }
                                } else {
                                    try {
                                        sdf3 = getNf(locale).parse(bol8);
                                        return new Float(sdf3.floatValue());
                                    } catch (ParseException var18) {
                                        throw new TypeCastException("Could not convert " + bol8 + " to " + type + ": ", var18);
                                    }
                                }
                            } else {
                                try {
                                    BigDecimal sdf4 = new BigDecimal(bol8);
                                    int retBig1 = bol8.indexOf(".");
                                    iscale = bol8.length();
                                    if(retBig1 > -1) {
                                        retBig1 = iscale - (retBig1 + 1);
                                        return sdf4.setScale(retBig1, 5);
                                    } else {
                                        return sdf4.setScale(0, 5);
                                    }
                                } catch (Exception var19) {
                                    throw new TypeCastException("Could not convert " + bol8 + " to " + type + ": ", var19);
                                }
                            }
                        } else {
                            try {
                                sdf3 = getNf(locale).parse(bol8);
                                return new Double(sdf3.doubleValue());
                            } catch (ParseException var20) {
                                throw new TypeCastException("Could not convert " + bol8 + " to " + type + ": ", var20);
                            }
                        }
                    } else {
                        sdf = null;
                        Boolean sdf2;
                        if(bol8.equalsIgnoreCase("TRUE")) {
                            sdf2 = new Boolean(true);
                        } else {
                            sdf2 = new Boolean(false);
                        }

                        return sdf2;
                    }
                } else {
                    return obj;
                }
            } else if(obj instanceof BigDecimal) {
                fromType = "BigDecimal";
                BigDecimal bol7 = (BigDecimal)obj;
                if("String".equals(type)) {
                    return getNf(locale).format(bol7.doubleValue());
                } else if(!"BigDecimal".equals(type) && !"java.math.BigDecimal".equals(type)) {
                    if("Double".equals(type)) {
                        return new Double(bol7.doubleValue());
                    } else if("Float".equals(type)) {
                        return new Float(bol7.floatValue());
                    } else if("Long".equals(type)) {
                        return new Long(Math.round(bol7.doubleValue()));
                    } else if("Integer".equals(type)) {
                        return new Integer((int)Math.round(bol7.doubleValue()));
                    } else {
                        throw new TypeCastException("Conversion from " + fromType + " to " + type + " not currently supported");
                    }
                } else {
                    return obj;
                }
            } else if(obj instanceof Double) {
                fromType = "Double";
                Double bol6 = (Double)obj;
                if(!"String".equals(type) && !"java.lang.String".equals(type)) {
                    if(!"Double".equals(type) && !"java.lang.Double".equals(type)) {
                        if(!"Float".equals(type) && !"java.lang.Float".equals(type)) {
                            if(!"Long".equals(type) && !"java.lang.Long".equals(type)) {
                                if(!"Integer".equals(type) && !"java.lang.Integer".equals(type)) {
                                    if(!"BigDecimal".equals(type) && !"java.math.BigDecimal".equals(type)) {
                                        throw new TypeCastException("Conversion from " + fromType + " to " + type + " not currently supported");
                                    } else {
                                        return new BigDecimal(bol6.toString());
                                    }
                                } else {
                                    return new Integer((int)Math.round(bol6.doubleValue()));
                                }
                            } else {
                                return new Long(Math.round(bol6.doubleValue()));
                            }
                        } else {
                            return new Float(bol6.floatValue());
                        }
                    } else {
                        return obj;
                    }
                } else {
                    return getNf(locale).format(bol6.doubleValue());
                }
            } else if(obj instanceof Float) {
                fromType = "Float";
                Float bol5 = (Float)obj;
                if("String".equals(type)) {
                    return getNf(locale).format(bol5.doubleValue());
                } else if(!"BigDecimal".equals(type) && !"java.math.BigDecimal".equals(type)) {
                    if("Double".equals(type)) {
                        return new Double(bol5.doubleValue());
                    } else if("Float".equals(type)) {
                        return obj;
                    } else if("Long".equals(type)) {
                        return new Long(Math.round(bol5.doubleValue()));
                    } else if("Integer".equals(type)) {
                        return new Integer((int)Math.round(bol5.doubleValue()));
                    } else {
                        throw new TypeCastException("Conversion from " + fromType + " to " + type + " not currently supported");
                    }
                } else {
                    return new BigDecimal(bol5.doubleValue());
                }
            } else if(obj instanceof Long) {
                fromType = "Long";
                Long bol4 = (Long)obj;
                if(!"String".equals(type) && !"java.lang.String".equals(type)) {
                    if(!"Double".equals(type) && !"java.lang.Double".equals(type)) {
                        if(!"Float".equals(type) && !"java.lang.Float".equals(type)) {
                            if(!"BigDecimal".equals(type) && !"java.math.BigDecimal".equals(type)) {
                                if(!"Long".equals(type) && !"java.lang.Long".equals(type)) {
                                    if(!"Integer".equals(type) && !"java.lang.Integer".equals(type)) {
                                        throw new TypeCastException("Conversion from " + fromType + " to " + type + " not currently supported");
                                    } else {
                                        return new Integer(bol4.intValue());
                                    }
                                } else {
                                    return obj;
                                }
                            } else {
                                return new BigDecimal(bol4.toString());
                            }
                        } else {
                            return new Float(bol4.floatValue());
                        }
                    } else {
                        return new Double(bol4.doubleValue());
                    }
                } else {
                    return getNf(locale).format(bol4.longValue());
                }
            } else if(obj instanceof Integer) {
                fromType = "Integer";
                Integer bol3 = (Integer)obj;
                if(!"String".equals(type) && !"java.lang.String".equals(type)) {
                    if(!"Double".equals(type) && !"java.lang.Double".equals(type)) {
                        if(!"Float".equals(type) && !"java.lang.Float".equals(type)) {
                            if(!"BigDecimal".equals(type) && !"java.math.BigDecimal".equals(type)) {
                                if(!"Long".equals(type) && !"java.lang.Long".equals(type)) {
                                    if(!"Integer".equals(type) && !"java.lang.Integer".equals(type)) {
                                        throw new TypeCastException("Conversion from " + fromType + " to " + type + " not currently supported");
                                    } else {
                                        return obj;
                                    }
                                } else {
                                    return new Long(bol3.longValue());
                                }
                            } else {
                                String sdf1 = bol3.toString();
                                retBig = new BigDecimal(bol3.doubleValue());
                                iscale = sdf1.indexOf(".");
                                int keylen = sdf1.length();
                                if(iscale > -1) {
                                    iscale = keylen - (iscale + 1);
                                    return retBig.setScale(iscale, 5);
                                } else {
                                    return retBig.setScale(0, 5);
                                }
                            }
                        } else {
                            return new Float(bol3.floatValue());
                        }
                    } else {
                        return new Double(bol3.doubleValue());
                    }
                } else {
                    return getNf(locale).format(bol3.longValue());
                }
            } else if(obj instanceof java.sql.Date) {
                fromType = "Date";
                java.sql.Date bol2 = (java.sql.Date)obj;
                if(!"String".equals(type) && !"java.lang.String".equals(type)) {
                    if(!"Date".equals(type) && !"java.sql.Date".equals(type)) {
                        if(!"Time".equals(type) && !"java.sql.Time".equals(type)) {
                            if(!"Timestamp".equals(type) && !"java.sql.Timestamp".equals(type)) {
                                throw new TypeCastException("Conversion from " + fromType + " to " + type + " not currently supported");
                            } else {
                                return new Timestamp(bol2.getTime());
                            }
                        } else {
                            throw new TypeCastException("Conversion from " + fromType + " to " + type + " not currently supported");
                        }
                    } else {
                        return obj;
                    }
                } else if(format != null && format.length() != 0) {
                    sdf = new SimpleDateFormat(format);
                    return sdf.format(new Date(bol2.getTime()));
                } else {
                    return bol2.toString();
                }
            } else if(obj instanceof Timestamp) {
                fromType = "Timestamp";
                Timestamp bol1 = (Timestamp)obj;
                if(!"String".equals(type) && !"java.lang.String".equals(type)) {
                    if(!"Date".equals(type) && !"java.sql.Date".equals(type)) {
                        if(!"Time".equals(type) && !"java.sql.Time".equals(type)) {
                            if(!"Timestamp".equals(type) && !"java.sql.Timestamp".equals(type)) {
                                throw new TypeCastException("Conversion from " + fromType + " to " + type + " not currently supported");
                            } else {
                                return obj;
                            }
                        } else {
                            return new Time(bol1.getTime());
                        }
                    } else {
                        return new java.sql.Date(bol1.getTime());
                    }
                } else if(format != null && format.length() != 0) {
                    sdf = new SimpleDateFormat(format);
                    return sdf.format(new Date(bol1.getTime()));
                } else {
                    return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(bol1).toString();
                }
            } else if(obj instanceof Boolean) {
                fromType = "Boolean";
                Boolean bol = (Boolean)obj;
                if(!"Boolean".equals(type) && !"java.lang.Boolean".equals(type)) {
                    if(!"String".equals(type) && !"java.lang.String".equals(type)) {
                        if(!"Integer".equals(type) && !"java.lang.Integer".equals(type)) {
                            throw new TypeCastException("Conversion from " + fromType + " to " + type + " not currently supported");
                        } else {
                            return bol.booleanValue()?new Integer(1):new Integer(0);
                        }
                    } else {
                        return bol.toString();
                    }
                } else {
                    return bol;
                }
            } else if(!"String".equals(type) && !"java.lang.String".equals(type)) {
                throw new TypeCastException("Conversion from " + obj.getClass().getName() + " to " + type + " not currently supported");
            } else {
                return obj.toString();
            }
        } else {
            return obj;
        }
    }

    private static NumberFormat getNf(Locale locale) {
        NumberFormat nf = null;
        if(locale == null) {
            nf = NumberFormat.getNumberInstance();
        } else {
            nf = NumberFormat.getNumberInstance(locale);
        }

        nf.setGroupingUsed(false);
        return nf;
    }

    public static Boolean convert2SBoolean(Object obj) throws TypeCastException {
        return (Boolean)convert(obj, "Boolean", (String)null);
    }

    public static Integer convert2Integer(Object obj) throws TypeCastException {
        return (Integer)convert(obj, "Integer", (String)null);
    }

    public static String convert2String(Object obj) throws TypeCastException {
        return (String)convert(obj, "String", (String)null);
    }

    public static String convert2String(Object obj, String defaultValue) throws TypeCastException {
        Object s = convert(obj, "String", (String)null);
        return s != null?(String)s:"";
    }

    public static Long convert2Long(Object obj) throws TypeCastException {
        return (Long)convert(obj, "Long", (String)null);
    }

    public static Double convert2Double(Object obj) throws TypeCastException {
        return (Double)convert(obj, "Double", (String)null);
    }

    public static BigDecimal convert2BigDecimal(Object obj, int scale) throws TypeCastException {
        return ((BigDecimal)convert(obj, "BigDecimal", (String)null)).setScale(scale, 5);
    }

    public static java.sql.Date convert2SqlDate(Object obj, String format) throws TypeCastException {
        return (java.sql.Date)convert(obj, "Date", format);
    }

    public static Timestamp convert2Timestamp(Object obj, String format) throws TypeCastException {
        return (Timestamp)convert(obj, "Timestamp", format);
    }
}

