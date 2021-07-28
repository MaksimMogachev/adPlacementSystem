package com.senlacourses.electronicHotelAdministrator.domain.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController  {

  @GetMapping(value = "/")
  public ResponseEntity<?> getMainPage() {
    return new ResponseEntity<>("This is main page",HttpStatus.OK);
  }
}
