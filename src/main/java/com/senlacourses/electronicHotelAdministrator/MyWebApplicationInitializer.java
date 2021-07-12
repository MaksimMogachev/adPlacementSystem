package com.senlacourses.electronicHotelAdministrator;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class MyWebApplicationInitializer implements WebApplicationInitializer {

  @Override
  public void onStartup(ServletContext servletContext) {

    AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
    context.register(ApplicationContextConfigurator.class);

    DispatcherServlet servlet = new DispatcherServlet(context);
    ServletRegistration.Dynamic registration = servletContext.addServlet("app", servlet);
    registration.setLoadOnStartup(1);
    registration.addMapping("/*");
  }
}
