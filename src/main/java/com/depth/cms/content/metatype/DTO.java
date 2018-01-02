package com.depth.cms.content.metatype;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * <p>@Title   </p>
 * <p>@Version 1.0.0 </p>
 * <p>@author chenjinxiu</p>
 * <p>@date 2017/11/22 11:27 </p>
 * <p>@Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */

public interface DTO extends Map, Serializable {
    Integer getAsInteger(String var1);

    Long getAsLong(String var1);

    String getAsString(String var1);

    String getString(Object var1);

    BigDecimal getAsBigDecimal(String var1);

    Date getAsDate(String var1);

    List getAsList(String var1);

    Timestamp getAsTimestamp(String var1);

    Boolean getAsBoolean(String var1);

    String toJson();

    void println();

    String toJson(String var1);
}

