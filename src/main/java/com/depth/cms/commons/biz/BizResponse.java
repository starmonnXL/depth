package com.depth.cms.commons.biz;

import java.io.Serializable;

/**
 * <p>@Title   </p>
 * <p>@Version 1.0.0 </p>
 * <p>@author chenjinxiu</p>
 * <p>@date 2017/11/22 13:59 </p>
 * <p>@Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */

public class BizResponse<T> implements Serializable {
    private static final long serialVersionUID = -3920581623450148208L;
    private boolean success = true;
    private String errMsg;
    private T obj;

    public BizResponse() {
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public void setError(String errMsg) {
        this.success = false;
        this.errMsg = errMsg;
    }

    public T getObj() {
        return this.obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}

