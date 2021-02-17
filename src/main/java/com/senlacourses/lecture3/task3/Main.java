package com.senlacourses.lecture3.task3;

public class Main {

  public static void main(String[] args) {
    IProduct product = new Product();
    IAssemblyLine assemblyLine = new AssemblyLine();

    product = assemblyLine.assembleProduct(product);
  }
}
