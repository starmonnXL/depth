package com.depth.cms.content.exception;

/**
 * <p>@Title   </p>
 * <p>@Version 1.0.0 </p>
 * <p>@author chenjinxiu</p>
 * <p>@date 2017/11/22 14:04 </p>
 * <p>@Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */

public class CommentException extends BizException {

    public static final BizException LACK_PARAM = new BizException(10030000,  "缺少参数,【%s】");

    public static final BizException PARAM_ERROR = new BizException(10030000,  "参数错误,【%s】");
}
