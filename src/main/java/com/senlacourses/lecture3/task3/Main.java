package com.senlacourses.lecture3.task3;

public class Main {

  public static void main(String[] args) {
    IProduct product = new Product();
    ILineStep buildBody = new LineStep();
    ILineStep buildChassis = new LineStep();
    ILineStep buildEngine = new LineStep();
    IAssemblyLine assemblyLine = new AssemblyLine(buildBody, buildChassis, buildEngine);

    product = assemblyLine.assembleProduct(product);
  }
}
