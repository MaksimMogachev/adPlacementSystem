package com.senlacourses.lecture3.task3;

import com.senlacourses.lecture3.task3.assemblyLine.AssemblyLine;
import com.senlacourses.lecture3.task3.assemblyLine.IAssemblyLine;
import com.senlacourses.lecture3.task3.lineStep.ILineStep;
import com.senlacourses.lecture3.task3.lineStep.LineStep;
import com.senlacourses.lecture3.task3.product.IProduct;
import com.senlacourses.lecture3.task3.product.Product;

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
