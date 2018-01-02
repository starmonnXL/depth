package com.depth.cms.content.service;

import com.depth.cms.content.exception.SDKException;
import com.depth.cms.content.metatype.DTO;

/**
 * <p>@Title  系统管理员接口 </p>
 * <p>@Version 1.0.0 </p>
 * <p>@author chenjinxiu</p>
 * <p>@date 2017/11/23 10:46 </p>
 * <p>@Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */

public interface UpmsFacade {

    /**
     * 新增管理员注册申请数据
     * @param parameter
     *
     * @throws SDKException
     */
    void insertUpmsRegister(DTO parameter) throws SDKException;

    /**
     * 审核管理员注册申请状态
     * @param parameter
     * @throws SDKException
     */
    void updateUpmsRegisterById(DTO parameter) throws SDKException;

    /**
     * 修改管理员信息
     * @param parameter
     * @throws SDKException
     */
    void updateUpmsUserById(DTO parameter) throws SDKException;

    /**
     * 根查询手机号是否注册
     * @param parameter
     * @return
     * @throws SDKException
     */
    DTO queryUpmsRegisterByPhone(DTO parameter) throws SDKException;

    /**
     * 根据账号和密码查询并登录
     * @param parameter
     * @return
     * @throws SDKException
     */
    DTO queryUpmsRegisterByPhoneAndPws(DTO parameter) throws SDKException;
}
