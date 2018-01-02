package com.depth.cms.content.biz;

import com.depth.cms.commons.util.DateUtil;
import com.depth.cms.commons.util.IdWorkerUtil;
import com.depth.cms.commons.util.PasswordUtils;
import com.depth.cms.commons.util.StringUtil;
import com.depth.cms.content.dao.UpmsDao;
import com.depth.cms.content.exception.CommentException;
import com.depth.cms.content.exception.SDKException;
import com.depth.cms.content.metatype.DTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>@Title   系统管理员逻辑层</p>
 * <p>@Version 1.0.0 </p>
 * <p>@author chenjinxiu</p>
 * <p>@date 2017/11/23 11:01 </p>
 * <p>@Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
@Service("upmsBiz")
public class UpmsBiz {

    @Resource
    private UpmsDao upmsDao;

    /**
     * 新增管理员注册申请数据
     * @param parameter
     * @throws SDKException
     */
    public void insertUpmsRegister(DTO parameter) throws SDKException {
        if(!StringUtil.isNotNull(parameter.get("phone")) || StringUtil.equals(parameter.getAsString("phone"),"")){
            throw CommentException.LACK_PARAM.newInstance("申请手机号不能为空");
        }
        if(!StringUtil.isNotNull(parameter.get("passWord")) || StringUtil.equals(parameter.getAsString("passWord"),"")){
            throw CommentException.LACK_PARAM.newInstance("登录密码不能为空");
        }
        if(!StringUtil.isNotNull(parameter.get("name")) || StringUtil.equals(parameter.getAsString("name"),"")){
            throw CommentException.LACK_PARAM.newInstance("真实姓名不能为空");
        }
        DTO result = upmsDao.queryUpmsRegisterByPhone(parameter);
        if(null != result){
            if(StringUtil.isNotNull(result.get("state")) && StringUtil.equals("0",result.getAsString("state"))) {
                throw CommentException.LACK_PARAM.newInstance("此账号正在申请中，请耐心等待！");
            }else{
                throw CommentException.LACK_PARAM.newInstance("此账号已存在!");
            }
        }
        String id = String.valueOf(IdWorkerUtil.getId());
        parameter.put("id",id);
        parameter.put("createTime", DateUtil.dateTimeToLong(new Date()));
        parameter.put("modifyTime", DateUtil.dateTimeToLong(new Date()));
        parameter.put("passWord", PasswordUtils.encrypt(parameter.getAsString("passWord")));
        upmsDao.insertUpmsRegister(parameter);

    }

    /**
     * 审核管理员注册申请状态
     * @param parameter
     * @throws SDKException
     */
    public void updateUpmsRegisterById(DTO parameter) throws SDKException {
        if(!StringUtil.isNotNull(parameter.get("id")) || StringUtil.equals(parameter.getAsString("id"),"")){
            throw CommentException.LACK_PARAM.newInstance("管理员申请标识不能为空");
        }
        if(!StringUtil.isNotNull(parameter.get("uid")) || StringUtil.equals(parameter.getAsString("uid"),"")){
            throw CommentException.LACK_PARAM.newInstance("审核管理员标识不能为空");
        }
        if(!StringUtil.isNotNull(parameter.get("uname")) || StringUtil.equals(parameter.getAsString("uname"),"")){
            throw CommentException.LACK_PARAM.newInstance("审核管理员真实姓名不能为空");
        }
        if(!StringUtil.isNotNull(parameter.get("state")) || StringUtil.equals(parameter.getAsString("state"),"")){
            throw CommentException.LACK_PARAM.newInstance("审核状态不能为空");
        }
        if(!StringUtil.isNotNull(parameter.get("level")) || StringUtil.equals(parameter.getAsString("level"),"")){
            throw CommentException.LACK_PARAM.newInstance("管理员等级不能为空");
        }
        parameter.put("modifyTime",DateUtil.dateTimeToLong(new Date()));
        upmsDao.updateUpmsRegisterById(parameter);
        // 审核通过，添加管理员信息
        if(StringUtil.equals("1",parameter.getAsString("state"))){
            // 根据管理员申请唯一标识查询申请数据
            DTO resultDTO = upmsDao.queryUpmsRegisterById(parameter);
            if(null != resultDTO){
                String id = String.valueOf(IdWorkerUtil.getId());
                parameter.put("id",id);
                parameter.put("userName",resultDTO.getAsString("phone"));
                parameter.put("passWord",resultDTO.getAsString("passWord"));
                parameter.put("name",resultDTO.getAsString("name"));
                parameter.put("createTime",DateUtil.dateTimeToLong(new Date()));
                parameter.put("modifyTime",DateUtil.dateTimeToLong(new Date()));
                parameter.put("phone",resultDTO.getAsString("phone"));
                parameter.put("state",0);
                upmsDao.insertUpmsUser(parameter);
            }
        }
    }

    /**
     * 修改管理员信息
     * @param parameter
     * @throws SDKException
     */
    public void updateUpmsUserById(DTO parameter) throws SDKException {
        if(!StringUtil.isNotNull(parameter.get("id")) || StringUtil.equals(parameter.getAsString("id"),"")){
            throw CommentException.LACK_PARAM.newInstance("管理员标识不能为空");
        }
        /**
         * TODO
         */
    }

    /**
     * 查询手机号是否注册
     * @param parameter
     * @return
     * @throws SDKException
     */
    public DTO queryUpmsRegisterByPhone(DTO parameter)throws SDKException{
        if(!StringUtil.isNotNull(parameter.get("phone")) || StringUtil.equals(parameter.getAsString("phone"),"")){
            throw CommentException.LACK_PARAM.newInstance("申请手机号不能为空");
        }
        DTO result = upmsDao.queryUpmsRegisterByPhone(parameter);
        return result;
    }

    /**
     * 登录查询
     * @param parameter
     * @return
     * @throws SDKException
     */
    public DTO queryUpmsRegisterByPhoneAndPws(DTO parameter)throws SDKException{
        if(!StringUtil.isNotNull(parameter.get("phone")) || StringUtil.equals(parameter.getAsString("phone"),"")){
            throw CommentException.LACK_PARAM.newInstance("申请手机号不能为空");
        }
        if(!StringUtil.isNotNull(parameter.get("passWord")) || StringUtil.equals(parameter.getAsString("passWord"),"")){
            throw CommentException.LACK_PARAM.newInstance("登录密码不能为空");
        }
        parameter.put("passWord", PasswordUtils.encrypt(parameter.getAsString("passWord")));
        DTO result = upmsDao.queryUpmsRegisterByPhoneAndPws(parameter);
        return result;
    }
}
