package com.senlacourses.lecture3.task3;

public class AssemblyLine implements IAssemblyLine{

  @Override
  public IProduct assembleProduct(IProduct product) {
    System.out.println("Start assembly");
    ILineStep lineStep = new LineStep();

    IProductPart bodyOfCar = lineStep.buildProductPart();
    IProductPart chassisOfCar = lineStep.buildProductPart();
    IProductPart engineOfCar = lineStep.buildProductPart();

    product.installFirstPart(bodyOfCar);
    product.installSecondPart(chassisOfCar);
    product.installThirdPart(engineOfCar);

    System.out.println("Car is ready!");
    return product;
  }
}
