package com.senlacourses.lecture3.task3;

public class Product implements IProduct{

  private IProductPart bodyOfCar;
  private IProductPart chassisOfCar;
  private IProductPart engineOfCar;

  @Override
  public void installFirstPart(IProductPart bodyOfCar) {
    this.bodyOfCar = bodyOfCar;
    System.out.println("Installed body of car");
  }

  @Override
  public void installSecondPart(IProductPart chassisOfCar) {
    this.chassisOfCar = chassisOfCar;
    System.out.println("Installed chassis of car");
  }

  @Override
  public void installThirdPart(IProductPart engineOfCar) {
    this.engineOfCar = engineOfCar;
    System.out.println("Installed engine of car");
  }
}
