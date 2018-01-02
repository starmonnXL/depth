package com.depth.cms.content.metatype.impl;

import com.depth.cms.content.metatype.DTO;
import com.depth.cms.content.metatype.util.JsonHelper;
import com.depth.cms.content.metatype.util.TypeCaseHelper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>@Title   </p>
 * <p>@Version 1.0.0 </p>
 * <p>@author chenjinxiu</p>
 * <p>@date 2017/11/22 11:28 </p>
 * <p>@Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */

public class BaseDTO extends HashMap implements DTO, Serializable {
    private static final long serialVersionUID = 1101684508376473931L;

    public BaseDTO() {
    }

    public BaseDTO(Map m) {
        super(m);
    }

    public BaseDTO(String key, Object value) {
        this.put(key, value);
    }

    public BigDecimal getAsBigDecimal(String key) {
        Object obj = TypeCaseHelper.convert(this.get(key), "BigDecimal", (String)null);
        return obj != null?(BigDecimal)obj:null;
    }

    public Date getAsDate(String key) {
        Object obj = TypeCaseHelper.convert(this.get(key), "Date", "yyyy-MM-dd");
        return obj != null?(Date)obj:null;
    }

    public Integer getAsInteger(String key) {
        Object obj = TypeCaseHelper.convert(this.get(key), "Integer", (String)null);
        return obj != null?(Integer)obj:null;
    }

    public Long getAsLong(String key) {
        Object obj = TypeCaseHelper.convert(this.get(key), "Long", (String)null);
        return obj != null?(Long)obj:null;
    }

    public String getAsString(String key) {
        Object obj = TypeCaseHelper.convert(this.get(key), "String", (String)null);
        return obj != null?(String)obj:"";
    }

    public String getString(Object key) {
        return (String)this.get(key);
    }

    public List getAsList(String key) {
        return (List)this.get(key);
    }

    public Timestamp getAsTimestamp(String key) {
        Object obj = TypeCaseHelper.convert(this.get(key), "Timestamp", "yyyy-MM-dd HH:mm:ss");
        return obj != null?(Timestamp)obj:null;
    }

    public Boolean getAsBoolean(String key) {
        Object obj = TypeCaseHelper.convert(this.get(key), "Boolean", (String)null);
        return obj != null?(Boolean)obj:null;
    }

    public String toJson() {
        String strJson = null;
        strJson = JsonHelper.encodeObject2Json(this);
        return strJson;
    }

    public String toJson(String pFormat) {
        String strJson = null;
        strJson = JsonHelper.encodeObject2Json(this, pFormat);
        return strJson;
    }

    public void println() {
        System.out.println(this);
    }
}
