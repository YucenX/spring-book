package com.farms4life2016.chapter05;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * a listener that will call a method on application start and application destruction
 * (the latter which we don't care about in this class)
 */
@WebListener
public class AnnotationContextListener implements ServletContextListener {
    
    @Override
    public void contextInitialized(ServletContextEvent event) {
        // upon the start of the servlet, we tell Spring to build an ApplicationContext
        ApplicationContext context = buildAnnotationContext();

        // and then we shove this application context into the servlet context
        event.getServletContext().setAttribute("context", context);
    }

    private ApplicationContext buildAnnotationContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        // builds a context and scans chapter03.mem03 for beans and components    
        context.scan("com.farms4life2016.chapter03.mem03"); // can also provide an array of packages
        context.refresh(); // tell program to start scanning
        return context;
    }

    // im not sure where or when this class is used, if at all. 

}
