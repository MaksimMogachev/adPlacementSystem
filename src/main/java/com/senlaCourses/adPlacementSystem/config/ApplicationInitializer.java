package com.senlaCourses.adPlacementSystem.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Spring application initializer.
 */
public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[]{ApplicationContextConfig.class};
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[]{WebApplicationContextConfig.class};
  }

  @Override
  protected String @NotNull [] getServletMappings() {
    return new String[]{"/"};
  }
}
