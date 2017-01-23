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

    @Override
    protected WebApplicationContext createRootApplicationContext() {
        return null;
    }

    @Override
    protected WebApplicationContext createServletApplicationContext() {
        XmlWebApplicationContext cxt = new XmlWebApplicationContext();
        cxt.setConfigLocation("/WEB-INF/spring/spring-mvc.xml");
        return cxt;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}
