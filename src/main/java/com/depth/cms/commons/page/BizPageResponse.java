package com.depth.cms.commons.page;

import com.depth.cms.commons.biz.BizResponse;

import java.io.Serializable;

/**
 * <p>@Title   </p>
 * <p>@Version 1.0.0 </p>
 * <p>@author chenjinxiu</p>
 * <p>@date 2017/11/22 13:58 </p>
 * <p>@Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */

public class BizPageResponse<T> extends BizResponse<T> implements Serializable {
    private static final long serialVersionUID = -4408151778025812996L;
    private Page page;

    public BizPageResponse() {
    }

    public Page getPage() {
        return this.page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
