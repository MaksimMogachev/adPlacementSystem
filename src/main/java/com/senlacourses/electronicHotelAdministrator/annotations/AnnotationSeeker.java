package com.senlacourses.electronicHotelAdministrator.annotations;

import java.util.Set;
import org.reflections.Reflections;

public class AnnotationSeeker {

  private final Reflections scanner;

  public AnnotationSeeker(String packageToScan) {
    this.scanner = new Reflections(packageToScan);
  }

  public Set<Class<?>> getAnnotatedClasses(Class annotation) {
    Set<Class<?>> classes = scanner.getTypesAnnotatedWith(annotation);
    if (classes.size() == 0) {
      throw new RuntimeException("No annotated classes");
    }
    return classes;
  }
}