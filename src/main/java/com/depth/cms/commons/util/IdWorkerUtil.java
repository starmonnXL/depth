package com.depth.cms.commons.util;

import com.depth.cms.commons.biz.IdWorker;

/**
 * <p>@Title   </p>
 * <p>@Version 1.0.0 </p>
 * <p>@author chenjinxiu</p>
 * <p>@date 2017/11/20 17:38 </p>
 * <p>@Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */

public class IdWorkerUtil {
    protected static final IdWorker idWorker = new IdWorker(1L, 1L);

    public IdWorkerUtil() {
    }

    public static Long getId() {
        return Long.valueOf(idWorker.nextId());
    }
}
