package com.senlacourses.electronicHotelAdministrator.annotations;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class InjectorOfDependency {

  private static final Map<Class<?>, Object> singletons = new HashMap();
  private static Set<Class<?>> classesWithAnnotation;
  private static final String defaultPathToDao = "com.senlacourses.electronicHotelAdministrator.dao";

  public static  <T> T getSingleton(Object obj)
      throws ClassNotFoundException, IllegalAccessException,
      InvocationTargetException, InstantiationException {
    Class<?> clazz = Class.forName(obj.getClass().getName());
    Class<?> fieldClass;
    Object fieldObject;

    if (classesWithAnnotation == null) {
      classesWithAnnotation = new AnnotationSeeker(defaultPathToDao)
          .getAnnotatedClasses(Singleton.class);
    }

    for (Field field : clazz.getDeclaredFields()) {
      if (field.isAnnotationPresent(ConfigSingleton.class)) {
        if (!classesWithAnnotation.contains(field.getType())) {
          throw new RuntimeException("the class of this field has no annotation");
        }
        fieldClass = Class.forName(field.getType().getName());
        field.setAccessible(true);

        if (singletons.containsKey(fieldClass)) {
          field.set(obj, singletons.get(fieldClass));
          continue;
        }

        fieldObject = getConstructor(fieldClass).newInstance();
        field.set(obj, fieldObject);
        singletons.put(fieldClass, fieldObject);
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
