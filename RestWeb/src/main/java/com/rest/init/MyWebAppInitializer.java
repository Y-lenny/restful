package com.rest.init;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

/**
 * <br></br>
 *
 * @author lennylv
 * @version 1.0
 * @class MyWebAppInitializer
 * @date 2017-1-20 17:23
 * @since 1.0
 */
public class MyWebAppInitializer extends AbstractDispatcherServletInitializer {

    /**
     * <br>覆盖父类的创建RootApplicationContext上下文</br>
     *
     * @param
     * @return org.springframework.web.context.WebApplicationContext
     * @throws
     * @author lennylv
     * @date 2017-3-17 10:21
     * @version 1.0
     * @since 1.0
     */
    @Override
    protected WebApplicationContext createRootApplicationContext() {
        return null;
    }

    /**
     * <br>覆盖父类创建ServletApplicationContext上下文</br>
     *
     * @param
     * @return org.springframework.web.context.WebApplicationContext
     * @throws
     * @author lennylv
     * @date 2017-3-17 10:23
     * @version 1.0
     * @since 1.0
     */
    @Override
    protected WebApplicationContext createServletApplicationContext() {
        XmlWebApplicationContext cxt = new XmlWebApplicationContext();
        cxt.setConfigLocation("/WEB-INF/spring/spring-mvc.xml");
        return cxt;
    }


    /**
     * <br>覆盖父类进行URLMapping拦截，然后发送到DispatcherServlet</br>
     *
     * @param
     * @return java.lang.String[]
     * @throws
     * @author lennylv
     * @date 2017-3-17 10:22
     * @version 1.0
     * @since 1.0
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }


}
