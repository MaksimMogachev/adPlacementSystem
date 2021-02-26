package com.senlacourses.lecture3.task3.lineStep;

import com.senlacourses.lecture3.task3.productPart.IProductPart;
import com.senlacourses.lecture3.task3.productPart.ProductPart;

public class LineStep implements ILineStep {

  @Override
  public IProductPart buildProductPart() {
    System.out.println("Built product part");
    return new ProductPart();
  }
}
