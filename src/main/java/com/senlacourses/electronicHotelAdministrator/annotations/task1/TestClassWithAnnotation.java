package com.senlacourses.electronicHotelAdministrator.annotations.task1;

import java.util.List;

public class TestClassWithAnnotation {

  @ConfigProperty
  private int someInt;
  @ConfigProperty
  private String someString;
  @ConfigProperty
  List<String> someList;

  @Override
  public String toString() {
    return "TestClassWithAnnotation{" + "someInt=" + someInt
        + ", someString='" + someString + '\'' + ", someList=" + someList + '}';
  }
}
