package com.depth.cms.member.interceptor;

import com.depth.cms.content.metatype.DTO;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>@Title CookieHandlerInterceptor</p>
 * <p>@Description cookie拦截器，用于拦截并创建Cookie信息</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Administrator</p>
 * <p>@date 2017年02月22日</p>
 * <p><a href="mailto:wangshilong@dgg.net">王仕龙</a></p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public class UserHandlerInterceptor extends HandlerInterceptorAdapter {


    /**
     * 在业务处理器处理请求之前被调用
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        // equalsIgnoreCase 与 equals的区别？
        if("GET".equalsIgnoreCase(request.getMethod())){
            //RequestUtil.saveRequest();
        }
        System.out.println("preHandle...");
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length());
        System.out.println("requestUri" + requestUri);
        System.out.println("contextPath" + contextPath);
        System.out.println("url" + url);
        DTO upms = (DTO) request.getSession().getAttribute("upms");
        if(null == upms){
            // 跳转到登录页面
            request.getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
            return false;
        }else{
            return true;
        }
    }
    // 在业务处理器处理请求完成之后，生成视图之前执行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
        System.out.println("postHandle...");
        if(modelAndView != null){
            Map<String, String> map = new HashMap<String, String>();
            modelAndView.addAllObjects(map);
        }
    }
    // 在DispatcherServlet完全处理完请求之后被调用，可用于清理资源
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception{
        System.out.println("afterCompletion...");
    }
}
