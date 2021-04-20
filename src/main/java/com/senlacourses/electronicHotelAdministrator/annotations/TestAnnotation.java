package com.senlacourses.electronicHotelAdministrator.annotations;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class TestAnnotation {

  @ConfigProperty() private int someInt;
  @ConfigProperty() private String someString;

  public static void main(String[] args)
      throws IllegalAccessException, InstantiationException, ClassNotFoundException,
          InvocationTargetException, IOException {
    TestAnnotation test = new TestAnnotation();

    test = (TestAnnotation) AnnotationAnalyzer.analyzer(test);
    System.out.println(test.someInt + " " + test.someString);
  }
}
