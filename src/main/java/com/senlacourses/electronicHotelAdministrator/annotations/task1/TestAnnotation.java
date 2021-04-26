package com.senlacourses.electronicHotelAdministrator.annotations.task1;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class TestAnnotation {

  @ConfigProperty
  TestClassWithAnnotation testObject;

  public static void main(String[] args)
      throws IllegalAccessException, InstantiationException, ClassNotFoundException,
          InvocationTargetException, IOException {
    TestAnnotation test = new TestAnnotation();

    test = AnnotationAnalyzer.analyzer(test);
    System.out.println(test.testObject.toString());
  }
}
