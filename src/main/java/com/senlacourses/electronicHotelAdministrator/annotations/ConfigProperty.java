package com.senlacourses.electronicHotelAdministrator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ConfigProperty {
  String configName() default "annotationConfig.properties";

  String propertyName() default "DEFAULT_PROPERTY_NAME";
}
