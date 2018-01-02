package com.depth.cms.commons.util;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * <p>@Title   </p>
 * <p>@Version 1.0.0 </p>
 * <p>@author chenjinxiu</p>
 * <p>@date 2017/11/22 14:21 </p>
 * <p>@Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */

public class ResourceUtils {
    private ResourceBundle resourceBundle;

    private ResourceUtils(String resource) {
        this.resourceBundle = ResourceBundle.getBundle(resource);
    }

    public static ResourceUtils getResource(String resource) {
        return new ResourceUtils(resource);
    }

    public String getValue(String key, Object... args) {
        String temp = this.resourceBundle.getString(key);
        return MessageFormat.format(temp, args);
    }

    public Map<String, String> getMap() {
        HashMap map = new HashMap();
        Iterator var2 = this.resourceBundle.keySet().iterator();

        while(var2.hasNext()) {
            String key = (String)var2.next();
            map.put(key, this.resourceBundle.getString(key));
        }

        return map;
    }
}
