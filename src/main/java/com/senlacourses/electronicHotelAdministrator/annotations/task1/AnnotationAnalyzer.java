package com.senlacourses.electronicHotelAdministrator.annotations.task1;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Properties;

/** Class for configuration object with annotation. */
public class AnnotationAnalyzer {

  private static final String defaultPropertyName = "DEFAULT_PROPERTY_NAME";

  /**
   * Main method
   *
   * @return configured object.
   */
  public static <T> T analyzer(Object obj)
      throws ClassNotFoundException, IllegalAccessException, InvocationTargetException,
          InstantiationException, IOException {
    Class<?> clazz = Class.forName(obj.getClass().getName());
    String parameterOfAnnotation;
    Properties properties = new Properties();

    for (Field field : clazz.getDeclaredFields()) {
      if (field.isAnnotationPresent(ConfigProperty.class)) {
        try (InputStream input =
            AnnotationAnalyzer.class
                .getClassLoader()
                .getResourceAsStream(field.getAnnotation(ConfigProperty.class).configName())) {
          properties.load(input);

          if (field
              .getAnnotation(ConfigProperty.class)
              .propertyName()
              .equals(defaultPropertyName)) {
            parameterOfAnnotation = clazz.getSimpleName() + "." + field.getName();
          } else {
            parameterOfAnnotation = field.getAnnotation(ConfigProperty.class).propertyName();
          }

          field.setAccessible(true);

          if (field.getType().getSimpleName()
              .equals(properties.getProperty(parameterOfAnnotation))) {
            Class<?> fieldClass = Class.forName(field.getType().getName());
            field.set(obj, analyzer(getConstructor(fieldClass).newInstance()));
          } else if (field.getType().isArray()) {
            field.set(obj, (properties.getProperty(parameterOfAnnotation)).split(","));
          } else if (field.getType().getSimpleName().equals("List")) {
            field.set(obj, Arrays.asList((properties
                .getProperty(parameterOfAnnotation)).split(",")));
          } else if (field.getType().getSimpleName().equals("int")) {
            field.setInt(obj, Integer.parseInt(properties.getProperty(parameterOfAnnotation)));
          } else {
            field.set(obj, field.getType().cast(properties.getProperty(parameterOfAnnotation)));
          }
        }
      }
    }

    return (T) obj;
  }

  private static Constructor<?> getConstructor(Class<?> clazz) throws IllegalAccessException {
    Constructor<?>[] constructors = clazz.getDeclaredConstructors();
    Constructor<?> constructor = null;
    boolean constructorWithZeroArguments = false;

    for (Constructor<?> cons : constructors) {
      if (cons.getParameterTypes().length == 0) {
        constructor = cons;
        constructorWithZeroArguments = true;
      }
    }

    if (!constructorWithZeroArguments) {
      throw new IllegalAccessException("No constructor with 0 arguments");
    }
    return constructor;
  }
}
