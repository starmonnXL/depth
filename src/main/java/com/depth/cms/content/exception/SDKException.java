package com.depth.cms.content.exception;

/**
 * <p>@Title   </p>
 * <p>@Version 1.0.0 </p>
 * <p>@author chenjinxiu</p>
 * <p>@date 2017/11/22 14:01 </p>
 * <p>@Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */

public class SDKException extends RuntimeException {
    private static final long serialVersionUID = 25779105158913270L;
    protected String msg;
    protected int code;

    protected SDKException() {
    }

    protected SDKException(String message, Throwable cause) {
        super(message, cause);
    }

    protected SDKException(Throwable cause) {
        super(cause);
    }

    protected SDKException(String message) {
        super(message);
        this.msg = message;
    }

    protected SDKException(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public int getCode() {
        return this.code;
    }
}

