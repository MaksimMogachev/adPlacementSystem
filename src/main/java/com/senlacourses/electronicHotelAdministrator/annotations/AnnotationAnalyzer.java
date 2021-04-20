package com.senlacourses.electronicHotelAdministrator.annotations;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/** Class for configuration object with annotation. */
public class AnnotationAnalyzer {

  /**
   * Main method
   *
   * @return configured object.
   */
  public static Object analyzer(Object obj)
      throws ClassNotFoundException, IllegalAccessException, InvocationTargetException,
          InstantiationException, IOException {

    Class<?> clazz = Class.forName(obj.getClass().getName());
    var constructors = clazz.getDeclaredConstructors();
    Constructor constructor = null;
    boolean constructorWithZeroArguments = false;
    String parameterOfAnnotation;
    String defaultPropertyName = "DEFAULT_PROPERTY_NAME";

    for (Constructor cons : constructors) {
      if (cons.getParameterTypes().length == 0) {
        constructor = cons;
        constructorWithZeroArguments = true;
      }
    }

    if (!constructorWithZeroArguments) {
      throw new IllegalAccessException("No constructor with 0 arguments");
    }

    Object object = constructor.newInstance();
    Field[] fields = clazz.getDeclaredFields();
    Properties properties = new Properties();

    for (Field field : fields) {
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

          if (field.getType().getName().equals("int")) {
            field.set(object, Integer.parseInt(properties.getProperty(parameterOfAnnotation)));
          } else {
            field.set(object, field.getType().cast(properties.getProperty(parameterOfAnnotation)));
          }
        }
      }
    }

    return object;
  }
}
