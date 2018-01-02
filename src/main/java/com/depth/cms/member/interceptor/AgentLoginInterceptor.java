package com.depth.cms.member.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 代理商身份拦截器
 */
public class AgentLoginInterceptor extends HandlerInterceptorAdapter{
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        DGGUtils.getWebManager().setHttpHeader(request, response);
//        if(MemberSecurityManager.getWebSecurityManager().checkToken() && checkAgentRole()) {
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
//    public String doUrl(HttpServletRequest request){
//        String urlPrefix = request.getScheme()+"://"+request.getServerName()+request.getContextPath();
//        return urlPrefix+"/wxpage/error.html?page=noAccess";
//    }
//
//    public boolean checkAgentRole(){
//        Subject subject = MemberSecurityManager.getSubject();
//        SessionTracker.Session session=subject.getSession();
//        Object agent=session.getAttribute("agent");
//        if (agent==null){
//            return false;
//        }
//        DTO dto = (DTO)agent;
//        return dto.containsKey("agentId");
//    }
}
