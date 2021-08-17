package com.senlaCourses.adPlacementSystem.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring web application context.
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.senlaCourses.adPlacementSystem.domain.controller")
public class WebApplicationContextConfig implements WebMvcConfigurer {

  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }
}
