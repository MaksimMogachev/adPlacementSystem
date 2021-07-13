package com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces;

import com.senlacourses.electronicHotelAdministrator.domain.model.Service;
import org.springframework.http.ResponseEntity;

public interface IServiceController {

  ResponseEntity<?> addNewService(Service service);

  ResponseEntity<?> showCurrentServices();

  ResponseEntity<?> changeServicePrice(String nameOfService, int newPrice);

  ResponseEntity<?> showPriceOfServicesAndRoomsByCriterion(String criterion);
}
