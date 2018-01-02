package com.depth.cms.controller;

import com.depth.cms.commons.util.StringUtil;
import com.depth.cms.content.metatype.DTO;
import com.depth.cms.content.service.UpmsFacade;
import com.depth.cms.controller.commons.BaseController;
import com.depth.cms.controller.commons.web.WebResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>@Title   </p>
 * <p>@Version 1.0.0 </p>
 * <p>@author chenjinxiu</p>
 * <p>@date 2017/11/23 10:11 </p>
 * <p>@Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
@Controller
@RequestMapping(value = "/admin")
public class LoginController extends BaseController{

    @Resource
    private UpmsFacade upmsFacade;

    /**
     * 跳转后台登录首页
     *
     * @return
     */
    @RequestMapping("/login.to")
    public ModelAndView loginIndex(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/login/login");
        return modelAndView;
    }

    /**
     * 跳转后台注册首页
     *
     * @return
     */
    @RequestMapping("/register.to")
    public ModelAndView registerIndex(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/login/register");
        return modelAndView;
    }

    /**
     * 验证登录信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/validateLogin.to")
    public WebResponse userLogin(HttpServletRequest request){
        WebResponse webResponse = new WebResponse();
        DTO dto = getParamAsDTO();
        HttpSession session = request.getSession();
        if(null != session){
            DTO result = (DTO)session.getAttribute("upms");
            if(null != result && StringUtil.isNotNull(result)){
                webResponse.setAjaxMsg(true,"用户已登录",null,null);
                return webResponse;
            }else{
                if(StringUtil.isNotNull(dto.get("phone")) && StringUtil.isNotNull(dto.get("passWord"))){
                    DTO resultDto = upmsFacade.queryUpmsRegisterByPhone(dto);
                    if(null == resultDto || !StringUtil.isNotNull(resultDto)){
                        webResponse.setAjaxMsg(false,"账号未注册！请先注册",null,null);
                        return webResponse;
                    }else{
                        DTO parms = upmsFacade.queryUpmsRegisterByPhoneAndPws(dto);
                        if(null == parms || !StringUtil.isNotNull(parms)){
                            webResponse.setAjaxMsg(false,"登录失败，账号或密码错误！",null,null);
                            return webResponse;
                        }else{
                            session.setAttribute("upms",parms);
                            webResponse.setAjaxMsg(true,"登录成功",null,null);
                            return webResponse;
                        }
                    }
                }
            }
        }
        return webResponse;
    }

    /**
     * 跳转后台首页
     *
     * @return
     */
    @RequestMapping("/index.do")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/login/index");
        return modelAndView;
    }
}
