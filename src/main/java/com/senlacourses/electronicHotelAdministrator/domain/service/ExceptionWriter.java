package com.senlacourses.electronicHotelAdministrator.domain.service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExceptionWriter {

  private static ExceptionWriter exceptionWriter = new ExceptionWriter();
  private FileWriter fileWriter;

  {
    try {
      fileWriter = new FileWriter("exceptions.txt");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static ExceptionWriter getInstance() {
    if(exceptionWriter == null){
      exceptionWriter = new ExceptionWriter();
    }
    return exceptionWriter;
  }

  public void writeException(String exception) {
    try {
      fileWriter
          .write(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
          + " - " + exception + ";\n");

      fileWriter.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
