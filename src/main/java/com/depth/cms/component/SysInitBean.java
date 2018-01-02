package com.depth.cms.component;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * 初始化类，在spring完成启动后会调用本类初始化
 * Created by DongQihong on 2016/12/24.
 */
@Component
public class SysInitBean implements InitializingBean, ServletContextAware {
    private ServletContext application;

    @Override
    public void afterPropertiesSet() throws Exception {
        application.setAttribute("urlPrefix", "/");
        application.setAttribute("icpNum", "京ICP备16006021号-1");
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        application = servletContext;
    }
}
