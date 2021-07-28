package com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces;

import com.senlacourses.electronicHotelAdministrator.domain.dto.request.UserDto;
import org.springframework.http.ResponseEntity;

public interface IRegistrationController {

  ResponseEntity<?> addNewUser(UserDto userDto);
}
