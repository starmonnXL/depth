package com.depth.cms.controller.commons;

import com.depth.cms.commons.page.Page;
import com.depth.cms.commons.util.ResourceUtils;
import com.depth.cms.content.metatype.DTO;
import com.depth.cms.content.metatype.impl.BaseDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>@Title   </p>
 * <p>@Version 1.0.0 </p>
 * <p>@author chenjinxiu</p>
 * <p>@date 2017/11/22 15:27 </p>
 * <p>@Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */

public class BaseController {
//    @Resource
//    private LoggerSendMsgFacade loggerSendMsgFacade;
    protected Log logger = LogFactory.getLog(this.getClass());
    private static final Log staticLogger = LogFactory.getLog(BaseController.class);

    public BaseController() {
    }

    protected HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    protected HttpServletResponse getResponse() {
        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        return response;
    }

//    @ExceptionHandler
//    @ResponseBody
//    public WebResponse BaseExceptionHandler(HttpServletRequest request, Exception ex) {
//        return staticBaseExceptionHandler(request, ex, this.loggerSendMsgFacade);
//    }

//    public static WebResponse staticBaseExceptionHandler(HttpServletRequest request, Throwable ex) {
//        WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
//        return staticBaseExceptionHandler(request, ex, (LoggerSendMsgFacade)applicationContext.getBean(LoggerSendMsgFacade.class));
//    }

//    public static WebResponse staticBaseExceptionHandler(HttpServletRequest request, Throwable ex, LoggerSendMsgFacade loggerSendMsgFacade) {
//        staticLogger.error("ExceptionHandler: ", ex);
//        WebResponse res = new WebResponse();
//        ExceptionLogEntity en = new ExceptionLogEntity();
//        StackTraceElement ste = Thread.currentThread().getStackTrace()[1];
//        String ip = getRemoteHost(request);
//        if(ex instanceof BizException) {
//            en.setMessage("【" + ((BizException)ex).getCode() + "】" + ((BizException)ex).getMsg());
//            res.setAjaxMsg(false, "【" + ((BizException)ex).getCode() + "】" + ((BizException)ex).getMsg(), (String)null, (String)null);
//        } else if(ex instanceof BizSystemException) {
//            en.setMessage("【" + ((BizSystemException)ex).getCode() + "】" + ((BizSystemException)ex).getMsg());
//            res.setAjaxMsg(false, "【" + ((BizSystemException)ex).getCode() + "】" + ((BizSystemException)ex).getMsg(), (String)null, (String)null);
//        } else if(ex instanceof RpcException) {
//            en.setMessage(ex.getLocalizedMessage());
//            res.setAjaxMsg(false, "远程服务调用异常，请重试!", (String)null, ex.getMessage());
//        } else {
//            en.setMessage(ex.getLocalizedMessage());
//            res.setAjaxMsg(false, "系统内部异常，请重试!", (String)null, ex.getMessage());
//        }
//
//        en.setClassName(ste.getClassName());
//        en.setIp(ip);
//        en.setMethodName(ste.getClassName() + "." + ste.getMethodName());
//        en.setSystemId(SecurityUtils.getWebSecurityManager().sid);
//        Subject subject = SecurityUtils.getSubject();
//        if(subject != null && !StringUtils.isEmpty(subject)) {
//            if(StringUtils.isEmpty(subject.getUserid())) {
//                en.setUserId("用户ID为null");
//            } else {
//                en.setUserId(subject.getUserid());
//            }
//        } else {
//            en.setUserId("用户ID为null");
//        }
//
//        en.setRequestUriMethod(getHandler(request));
//        loggerSendMsgFacade.sendExceptionLogMsg(en);
//        return res;
//    }

    public static String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
    }

    protected String rsaEncryptByPrivateKey(String s) {
        return s;
    }

//    protected static void logBefore(Logger logger, String interfaceName) {
//        logger.info("");
//        logger.info("start");
//        logger.info(interfaceName);
//    }
//
//    protected static void logAfter(Logger logger) {
//        logger.info("end");
//        logger.info("");
//    }

//    protected WebResponse webResponse(MenuEnums me, ReturnInterface t) {
//        try {
//            SecurityUtils.getWebSecurityManager().checkAuthorization(me);
//            return t.webResponse();
//        } catch (PermissionsException var5) {
//            var5.printStackTrace();
//            WebResponse wb = new WebResponse();
//            wb.put("status", Boolean.valueOf(false));
//            wb.put("info", "您没有权限访问该功能!");
//            wb.put("rows", new ArrayList());
//            wb.put("total", Integer.valueOf(0));
//            return wb;
//        }
//    }

    protected Page getPage(DTO dto) {
        Page p = new Page();
        p.setPageDTO(dto);
        return p;
    }

    public ModelAndView getModelAndView() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("fileprifix", ResourceUtils.getResource("global").getMap().get("dgg.file.fileprifix"));
        return mv;
    }

//    public ModelAndView getModelAndView(MenuEnums me) {
//        ModelAndView mv = new ModelAndView();
//        ArrayList codes = SecurityUtils.getWebSecurityManager().getArightsForCodes(me);
//        mv.addObject("fileprifix", ResourceUtils.getResource("global").getMap().get("dgg.file.fileprifix"));
//        mv.addObject("hrightsCodes", codes);
//        return mv;
//    }

    public String getFileprifix() {
        return (String) ResourceUtils.getResource("global").getMap().get("dgg.file.fileprifix");
    }

//    public ModelAndView getModelAndView(MenuEnums me, boolean isVerify) {
//        ModelAndView mv = new ModelAndView();
//        ArrayList codes;
//        if(isVerify) {
//            try {
//                SecurityUtils.getWebSecurityManager().checkAuthorization(me);
//                codes = SecurityUtils.getWebSecurityManager().getArightsForCodes(me);
//                mv.addObject("fileprifix", ResourceUtils.getResource("global").getMap().get("dgg.file.fileprifix"));
//                mv.addObject("hrightsCodes", JSONArray.toJSONString(codes));
//                return mv;
//            } catch (PermissionsException var5) {
//                var5.printStackTrace();
//                mv.setViewName("admin/system/nopermission");
//                return mv;
//            }
//        } else {
//            codes = SecurityUtils.getWebSecurityManager().getArightsForCodes(me);
//            mv.addObject("fileprifix", ResourceUtils.getResource("global").getMap().get("dgg.file.fileprifix"));
//            mv.addObject("hrightsCodes", JSONArray.toJSONString(codes));
//            return mv;
//        }
//    }

    protected DTO getParamAsDTO() {
        BaseDTO dto = new BaseDTO();
        Enumeration paramNames = this.getRequest().getParameterNames();

        while(paramNames.hasMoreElements()) {
            String paramName = (String)paramNames.nextElement();
            String paramValues = this.getRequest().getParameter(paramName);
            dto.put(paramName, paramValues);
        }

        return dto;
    }

    protected Map getParamAsMap() {
        HashMap dto = new HashMap();
        Enumeration paramNames = this.getRequest().getParameterNames();

        while(paramNames.hasMoreElements()) {
            String paramName = (String)paramNames.nextElement();
            String paramValues = this.getRequest().getParameter(paramName);
            dto.put(paramName, paramValues);
        }

        return dto;
    }

    protected DTO getParamAsDTO(String useridKey) {
        DTO dto = this.getParamAsDTO();
        dto.put(useridKey, getToken(dto));
        return dto;
    }

    protected DTO getDecodeParamAsDto(String useridKey) {
        DTO dto = this.getParamAsDTO();
        dto.put(useridKey, getDecodeToken(dto));
        return dto;
    }

    protected static String getToken(DTO dto) {
        return dto.containsKey("token")?dto.getAsString("token"):"";
    }

    protected static String getDecodeToken(DTO dto) {
        return dto.containsKey("token")?dto.getAsString("token"):"";
    }

    protected String httpRequestUrl() {
        String path = this.getRequest().getContextPath();
        String basePath = this.getRequest().getScheme() + "://" + this.getRequest().getServerName() + ":" + this.getRequest().getServerPort() + path + "/";
        return basePath;
    }


    private static final String getHandler(final HttpServletRequest request) {
        final String uri = request.getRequestURI();
        final String method = request.getMethod();
        WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
        String[] beanNames = applicationContext.getBeanNamesForType(RequestMappingHandlerMapping.class);
        HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(request) {
            public String getRequestURI() {
                return uri;
            }

            public StringBuffer getRequestURL() {
                return new StringBuffer(super.getRequestURL().toString().replace(super.getRequestURI(), uri));
            }

            public String getServletPath() {
                return super.getServletPath().replace(super.getRequestURI(), uri);
            }

            public String getMethod() {
                return method != null && !"".equals(method)?method:super.getMethod();
            }
        };
        StringBuilder uriMapMethod = new StringBuilder();
        uriMapMethod.append(httpServletRequestWrapper.getRequestURI()).append(": [");
        if(beanNames != null) {
            String[] var7 = beanNames;
            int var8 = beanNames.length;

            for(int var9 = 0; var9 < var8; ++var9) {
                String beanName = var7[var9];
                RequestMappingHandlerMapping mapping = (RequestMappingHandlerMapping)applicationContext.getBean(beanName, RequestMappingHandlerMapping.class);

                try {
                    HandlerExecutionChain e = mapping.getHandler(httpServletRequestWrapper);
                    if(e != null) {
                        Object handler = e.getHandler();
                        if(handler instanceof HandlerMethod) {
                            HandlerMethod hm = (HandlerMethod)handler;
                            uriMapMethod.append(hm);
                        } else if(handler instanceof Controller) {
                            Controller var18 = (Controller)handler;
                            Class hmClass = var18.getClass();
                            uriMapMethod.append(hmClass.getDeclaredMethod("handleRequest", new Class[]{HttpServletRequest.class, HttpServletResponse.class}));
                        } else {
                            uriMapMethod.append(handler.getClass().getName());
                        }
                        break;
                    }
                } catch (HttpRequestMethodNotSupportedException var16) {
                    staticLogger.error("not find http request method.", var16);
                } catch (Exception var17) {
                    staticLogger.error("get uri mapping error.", var17);
                }
            }
        }

        return uriMapMethod.append("]").toString();
    }
}

