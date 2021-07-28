package com.senlacourses.electronicHotelAdministrator.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[] {ApplicationContextConfigurator.class};
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[] {WebApplicationContextConfigurator.class};
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] {"/"};
  }
}