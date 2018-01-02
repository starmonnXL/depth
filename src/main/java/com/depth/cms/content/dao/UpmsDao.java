package com.depth.cms.content.dao;

import com.depth.cms.content.metatype.DTO;

/**
 * <p>@Title   系统管理员Dao</p>
 * <p>@Version 1.0.0 </p>
 * <p>@author chenjinxiu</p>
 * <p>@date 2017/11/23 11:01 </p>
 * <p>@Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */

public interface UpmsDao {

    int insertUpmsRegister(DTO parameter);

    int updateUpmsRegisterById(DTO parameter);

    int insertUpmsUser(DTO parameter);

    int updateUpmsUserById(DTO parameter);

    DTO queryUpmsRegisterById(DTO parameter);

    DTO queryUpmsRegisterByPhone(DTO parameter);

    DTO queryUpmsRegisterByPhoneAndPws(DTO parameter);

}
