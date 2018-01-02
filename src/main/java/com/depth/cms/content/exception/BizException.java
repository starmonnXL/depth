package com.depth.cms.content.exception;

/**
 * <p>@Title   </p>
 * <p>@Version 1.0.0 </p>
 * <p>@author chenjinxiu</p>
 * <p>@date 2017/11/22 14:01 </p>
 * <p>@Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */

public class BizException extends SDKException {
    private static final long serialVersionUID = -5875371379845226068L;
    public static final BizException INSTANCE = new BizException(99980000, "%s");
    public static final BizException PARAM_IS_NULL = new BizException(99980001, "参数对象为空,参数名称【%s】");
    public static final BizException PARAM_VERIFY_FAIL = new BizException(99980002, "参数校验失败,参数名称【%s】");
    public static final BizException TOKEN_IS_ILLICIT = new BizException(99980003, "Token验证非法,【%s】");

    protected BizException(int code, String msgFormat, Object args) {
        super(String.format(msgFormat, new Object[]{args}));
        this.code = code;
        this.msg = String.format(msgFormat, new Object[]{args});
    }

    protected BizException() {
    }

    public BizException newInstance(Object args) {
        return new BizException(this.code, this.msg, args);
    }

    protected BizException(String message, Throwable cause) {
        super(message, cause);
    }

    protected BizException(Throwable cause) {
        super(cause);
    }

    public BizException(int code, String message) {
        super(message);
        this.code = code;
    }

    protected BizException(int code) {
        this.code = code;
    }
}

