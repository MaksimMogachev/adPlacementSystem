package com.senlacourses.lecture3.task3.assemblyLine;

import com.senlacourses.lecture3.task3.lineStep.ILineStep;
import com.senlacourses.lecture3.task3.product.IProduct;
import com.senlacourses.lecture3.task3.productPart.IProductPart;

public class AssemblyLine implements IAssemblyLine{

  private ILineStep buildBody;
  private ILineStep buildChassis;
  private ILineStep buildEngine;

  public AssemblyLine(ILineStep buildBody, ILineStep buildChassis, ILineStep buildEngine) {
    this.buildBody = buildBody;
    this.buildChassis = buildChassis;
    this.buildEngine = buildEngine;
  }

  @Override
  public IProduct assembleProduct(IProduct product) {
    System.out.println("Start assembly\n");

    IProductPart body = buildBody.buildProductPart();
    IProductPart chassis = buildChassis.buildProductPart();
    IProductPart engine = buildEngine.buildProductPart();

    product.installFirstPart(body);
    product.installSecondPart(chassis);
    product.installThirdPart(engine);

    System.out.println("\nCar is ready!");
    return product;
  }
}
