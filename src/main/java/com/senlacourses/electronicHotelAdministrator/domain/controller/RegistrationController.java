package com.senlacourses.electronicHotelAdministrator.domain.controller;

import com.senlacourses.electronicHotelAdministrator.config.security.jwt.JwtProvider;
import com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces.IRegistrationController;
import com.senlacourses.electronicHotelAdministrator.domain.dto.request.UserDto;
import com.senlacourses.electronicHotelAdministrator.domain.model.User;
import com.senlacourses.electronicHotelAdministrator.domain.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController implements IRegistrationController {

  private final UserDetailsServiceImpl userService;
  @Autowired
  private JwtProvider jwtProvider;

  public RegistrationController(UserDetailsServiceImpl userService) {
    this.userService = userService;
  }

  @Override
  @PostMapping(value = "/registration")
  public ResponseEntity<?> addNewUser(@RequestBody UserDto userDto) {
    userService.addNewUser(userDto);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PostMapping("/login")
  public ResponseEntity<?> auth(@RequestBody UserDto request) {
    User user = userService.findByLoginAndPassword(request.getUsername(), request.getPassword());
    String token = jwtProvider.generateToken(user.getUsername());

    return new ResponseEntity<>(token, HttpStatus.OK);
  }
}
