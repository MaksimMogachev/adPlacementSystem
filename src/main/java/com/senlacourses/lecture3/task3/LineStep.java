package com.senlacourses.lecture3.task3;

public class LineStep implements ILineStep {

  @Override
  public IProductPart buildProductPart() {
    System.out.println("Built product part");
    return new ProductPart();
  }
}
