package com.depth.cms.content.metatype;

import com.depth.cms.content.metatype.util.BocUtils;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>@Title   </p>
 * <p>@Version 1.0.0 </p>
 * <p>@author chenjinxiu</p>
 * <p>@date 2017/11/22 11:42 </p>
 * <p>@Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */

public class JsonValueProcessorImpl implements JsonValueProcessor {
    private String format = "yyyy-MM-dd HH:mm:ss";

    public JsonValueProcessorImpl() {
    }

    public JsonValueProcessorImpl(String format) {
        this.format = format;
    }

    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        String[] obj = new String[0];
        SimpleDateFormat sf;
        int i;
        if(value instanceof Date[]) {
            sf = new SimpleDateFormat(this.format);
            Date[] dates = (Date[])((Date[])value);
            obj = new String[dates.length];

            for(i = 0; i < dates.length; ++i) {
                obj[i] = sf.format(dates[i]);
            }
        }

        if(value instanceof Timestamp[]) {
            sf = new SimpleDateFormat(this.format);
            Timestamp[] var7 = (Timestamp[])((Timestamp[])value);
            obj = new String[var7.length];

            for(i = 0; i < var7.length; ++i) {
                obj[i] = sf.format(var7[i]);
            }
        }

        if(value instanceof java.sql.Date[]) {
            sf = new SimpleDateFormat(this.format);
            java.sql.Date[] var8 = (java.sql.Date[])((java.sql.Date[])value);
            obj = new String[var8.length];

            for(i = 0; i < var8.length; ++i) {
                obj[i] = sf.format(var8[i]);
            }
        }

        return obj;
    }

    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
        if(BocUtils.isEmpty(value)) {
            return "";
        } else {
            String str;
            if(value instanceof Timestamp) {
                str = (new SimpleDateFormat(this.format)).format((Timestamp)value);
                return str;
            } else if(value instanceof Date) {
                str = (new SimpleDateFormat(this.format)).format((Date)value);
                return str;
            } else if(value instanceof java.sql.Date) {
                str = (new SimpleDateFormat(this.format)).format((java.sql.Date)value);
                return str;
            } else {
                return value.toString();
            }
        }
    }

    public String getFormat() {
        return this.format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}

