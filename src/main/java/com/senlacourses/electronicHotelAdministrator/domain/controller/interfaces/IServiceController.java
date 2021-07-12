package com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces;

import org.springframework.http.ResponseEntity;

public interface IServiceController {

  ResponseEntity<?> addNewService(String name, int price);

  ResponseEntity<?> showCurrentServices();

  ResponseEntity<?> changeServicePrice(String nameOfService, int newPrice);

  ResponseEntity<?> showPriceOfServicesAndRoomsByCriterion(String criterion);
}
