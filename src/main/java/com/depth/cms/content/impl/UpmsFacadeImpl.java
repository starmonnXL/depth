package com.depth.cms.content.impl;

import com.depth.cms.content.biz.UpmsBiz;
import com.depth.cms.content.exception.SDKException;
import com.depth.cms.content.metatype.DTO;
import com.depth.cms.content.service.UpmsFacade;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>@Title   系统管理员接口实现</p>
 * <p>@Version 1.0.0 </p>
 * <p>@author chenjinxiu</p>
 * <p>@date 2017/11/23 11:00 </p>
 * <p>@Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
@Service("upmsFacade")
public class UpmsFacadeImpl implements UpmsFacade{

    @Resource
    private UpmsBiz upmsBiz;

    @Override
    public void insertUpmsRegister(DTO parameter){
        upmsBiz.insertUpmsRegister(parameter);
    }

    @Override
    public void updateUpmsRegisterById(DTO parameter){
        upmsBiz.updateUpmsRegisterById(parameter);
    }

    @Override
    public void updateUpmsUserById(DTO parameter){
        upmsBiz.updateUpmsUserById(parameter);
    }

    @Override
    public DTO queryUpmsRegisterByPhone(DTO parameter) throws SDKException {
        return upmsBiz.queryUpmsRegisterByPhone(parameter);
    }

    @Override
    public DTO queryUpmsRegisterByPhoneAndPws(DTO parameter) throws SDKException {
        return upmsBiz.queryUpmsRegisterByPhoneAndPws(parameter);
    }
}
