package com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces;

import com.senlacourses.electronicHotelAdministrator.domain.dto.request.ServiceDto;
import org.springframework.http.ResponseEntity;

public interface IServiceController {

  ResponseEntity<?> addNewService(ServiceDto serviceDto);

  ResponseEntity<?> showCurrentServices();

  ResponseEntity<?> changeServicePrice(String nameOfService, int newPrice);

  ResponseEntity<?> showPriceOfServicesAndRoomsByCriterion(String criterion);
}
