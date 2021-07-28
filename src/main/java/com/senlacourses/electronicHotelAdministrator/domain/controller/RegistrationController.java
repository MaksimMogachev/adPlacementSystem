package com.senlacourses.electronicHotelAdministrator.domain.controller;

import com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces.IRegistrationController;
import com.senlacourses.electronicHotelAdministrator.domain.dto.request.UserDto;
import com.senlacourses.electronicHotelAdministrator.domain.service.interfaces.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController implements IRegistrationController {

  private final IUserService userService;

  public RegistrationController(IUserService userService) {
    this.userService = userService;
  }

  @Override
  @PostMapping(value = "/registration")
  public ResponseEntity<?> addNewUser(@RequestBody UserDto userDto) {
    userService.addNewUser(userDto);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
