package com.senlacourses.electronicHotelAdministrator.annotations.task1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ConfigProperty {
  String defaultConfig = "annotationConfig.properties";
  String defaultPropertyName = "DEFAULT_PROPERTY_NAME";

  String configName() default defaultConfig;

  String propertyName() default defaultPropertyName;
}
