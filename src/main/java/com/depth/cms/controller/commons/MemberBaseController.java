package com.depth.cms.controller.commons;

import com.depth.cms.commons.page.Page;
import com.depth.cms.commons.util.ResourceUtils;
import com.depth.cms.content.metatype.DTO;
import com.depth.cms.content.metatype.impl.BaseDTO;
import com.depth.cms.content.metatype.util.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * <p>@Title   </p>
 * <p>@Version 1.0.0 </p>
 * <p>@author chenjinxiu</p>
 * <p>@date 2017/11/22 14:19 </p>
 * <p>@Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */

public class MemberBaseController {
//    @Resource
//    private LoggerSendMsgFacade loggerSendMsgFacade;
//    @Resource
//    private KafkaProducerServer kafkaProducerServer;
    protected static Log logger = LogFactory.getLog(MemberBaseController.class);

    public MemberBaseController() {
    }

    protected HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    protected HttpServletResponse getResponse() {
        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        return response;
    }

//    public static WebResponse staticBaseExceptionHandler(HttpServletRequest request, Throwable ex) {
//        WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
//        return staticBaseExceptionHandler(request, ex, (LoggerSendMsgFacade)applicationContext.getBean(LoggerSendMsgFacade.class));
//    }
//
//    public static WebResponse staticBaseExceptionHandler(HttpServletRequest request, Throwable ex, LoggerSendMsgFacade loggerSendMsgFacade) {
//        logger.error("ExceptionHandler: ", ex);
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
//            StringBuilder subject = new StringBuilder(ex.getLocalizedMessage());
//            subject.insert(0, "远程服务调用异常：");
//            en.setMessage(subject.toString());
//            res.setAjaxMsg(false, "远程服务调用异常，请重试!", (String)null, (String)null);
//        } else {
//            en.setMessage(ex.getLocalizedMessage());
//            res.setAjaxMsg(false, "系统内部异常，请重试!", (String)null, (String)null);
//        }
//
//        en.setClassName(ste.getClassName());
//        en.setIp(ip);
//        en.setMethodName(ste.getClassName() + "." + ste.getMethodName());
//        en.setSystemId(getSystemId());
//        Subject subject1 = MemberSecurityManager.getSubject();
//        if(subject1 != null && !StringUtils.isEmpty(subject1.getUserid())) {
//            en.setUserId(subject1.getUserid());
//        } else {
//            en.setUserId("用户ID为null");
//        }
//
//        loggerSendMsgFacade.sendExceptionLogMsg(en);
//        return res;
//    }
//
//    @ExceptionHandler
//    @ResponseBody
//    public WebResponse BaseExceptionHandler(HttpServletRequest request, Exception ex) {
//        return staticBaseExceptionHandler(request, ex, this.loggerSendMsgFacade);
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

    protected static void logBefore(Logger logger, String interfaceName) {
        logger.info("");
        logger.info("start");
        logger.info(interfaceName);
    }

    protected static void logAfter(Logger logger) {
        logger.info("end");
        logger.info("");
    }

    protected Page getPage(DTO dto) {
        Page p = new Page();
        p.setPageDTO(dto);
        return p;
    }

    public ModelAndView getModelAndView() {
        ModelAndView mv = new ModelAndView();
        return mv;
    }

    public String getFileprifix() {
        return (String)ResourceUtils.getResource("global").getMap().get("dgg.file.fileprifix");
    }

    public static String getSystemId() {
        return (String) ResourceUtils.getResource("global").getMap().get("operate.sysid");
    }

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

//    protected void operationLog(String sid, String updateBefore, String updateAfter, String className, String operationName, String params) {
//        OperationLogEntity operationLogEntity = new OperationLogEntity();
//        operationLogEntity.setSid(sid);
//        operationLogEntity.setUpdateBefore(updateBefore);
//        operationLogEntity.setUpdateAfter(updateAfter);
//        operationLogEntity.setClassName(className);
//        operationLogEntity.setOperationName(operationName);
//        operationLogEntity.setParams(params);
//        operationLogEntity.setUserId(MemberSecurityManager.getSubject().getUserid());
//        operationLogEntity.setCreateTime(new Date());
//        operationLogEntity.setUserName(MemberSecurityManager.getSubject().getUsername());
//        this.loggerSendMsgFacade.sendOperationLogMsg(operationLogEntity);
//    }
//
//    protected String getSiteId() {
//        return getSiteId(this.getRequest());
//    }
//
//    public static String getSiteId(HttpServletRequest request) {
//        Object siteId = request.getAttribute("siteId");
//        if(siteId == null || org.apache.commons.lang3.StringUtils.isBlank(siteId.toString())) {
//            siteId = "100000";
//        }
//
//        request.setAttribute("siteId", siteId.toString());
//        return siteId.toString();
//    }

//    public static String getSiteName(HttpServletRequest request) {
//        Object siteName = request.getAttribute("siteName");
//        if(siteName == null || org.apache.commons.lang3.StringUtils.isBlank(siteName.toString())) {
//            OperateSiteFacade operateSiteFacade = (OperateSiteFacade)ContextLoader.getCurrentWebApplicationContext().getBean("operateSiteFacade", OperateSiteFacade.class);
//            DTO siteInfo = operateSiteFacade.loadSiteInfo(getSiteId(request));
//            siteName = siteInfo.getAsString("siteName");
//        }
//
//        request.setAttribute("siteName", siteName.toString());
//        return siteName.toString();
//    }

//    public void secordRequestMsg() {
//        HttpServletRequest request = this.getRequest();
//        request.setAttribute("pageUUID", UUID.randomUUID().toString());
//        this.kafkaProducerServer.sendMessageForReferer(request);
//    }
}

