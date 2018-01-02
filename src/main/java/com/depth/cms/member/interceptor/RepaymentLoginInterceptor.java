package com.depth.cms.member.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * <p>@Title 还款登录拦截 </p>
 * <p>@Description 还款登录拦截 </p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author weifeng 作者</p>
 * <p>@date 2017/8/14 15:07 创建日期</p>
 * <p>weifeng@dgg.net 作者的公司邮箱</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public class RepaymentLoginInterceptor extends HandlerInterceptorAdapter {
    private static final String WX_LOGIN_SUBPAGEPATH="WX_LOGIN_SUBPAGEPATH";

//    private final static String systemId = ResourceUtils.getResource("global").getMap().get("agent.sysid");
//    @Resource
//    private SystemFacade systemFacade;
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        DGGUtils.getWebManager().setHttpHeader(request, response);
//        if(MemberSecurityManager.getWebSecurityManager().checkToken() && checkAgentRole(request)) {
//            Subject subject = MemberSecurityManager.getSubject();
//            if(subject == null || !subject.isAuthenticated()) {
//                response.sendRedirect(doUrl(request));
//            }else{
//                //更新session时间
//                MemberSecurityManager.getWebSecurityManager().setSubjectExpire();
//                //设置
//                request.setAttribute("member",MemberSecurityManager.getSubject().getSession().getAttribute("member"));
//                return true;
//            }
//        }else{
//            response.sendRedirect(doUrl(request));
//        }
//        return false;
//    }
//
//    //检查代理商身份
//    private boolean checkAgentRole(HttpServletRequest request){
//        if ("true".equals(request.getAttribute("agentCheck"))){
//            Subject subject = MemberSecurityManager.getSubject();
//            Session  session=subject.getSession();
//            Object agent=session.getAttribute("agent");
//            if (agent==null){
//                return false;
//            }
//            DTO dto = (DTO)agent;
//            return dto.containsKey("agentId");
//        }
//        return true;
//    }
//
//    private String doUrl(HttpServletRequest request){
//        String subRedirect="";
//        try{
//            subRedirect=systemFacade.loadByKey(systemId,WX_LOGIN_SUBPAGEPATH).getSetvalue();
//        }catch (Exception e){
//            PluginFactory.getLogPlugin().error(RepaymentLoginInterceptor.class,"[doUrl]",e);
//            subRedirect="http://xiaochengxu.dgg.net/mp/wx6ef9ea688491311e/scope-snsapi_base.mp?key=KEY_COODE_4";
//        }
//        return subRedirect;
//    }
}
