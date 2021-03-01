package com.senlacourses.lecture3.task3.product;

import com.senlacourses.lecture3.task3.productPart.IProductPart;

public interface IProduct {

  void installFirstPart(IProductPart iProductPart);
  void installSecondPart(IProductPart iProductPart);
  void installThirdPart(IProductPart iProductPart);

}
