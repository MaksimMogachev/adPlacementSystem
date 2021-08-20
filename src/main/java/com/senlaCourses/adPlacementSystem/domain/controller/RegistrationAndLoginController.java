package com.senlaCourses.adPlacementSystem.domain.controller;

import com.senlaCourses.adPlacementSystem.config.jwt.JwtProvider;
import com.senlaCourses.adPlacementSystem.domain.controller.interfaces.IRegistrationAndLoginController;
import com.senlaCourses.adPlacementSystem.domain.dto.request.LoginDto;
import com.senlaCourses.adPlacementSystem.domain.dto.request.UserDto;
import com.senlaCourses.adPlacementSystem.domain.model.User;
import com.senlaCourses.adPlacementSystem.domain.service.interfaces.IUserService;
import com.senlaCourses.adPlacementSystem.exceptions.EntityAlreadyExistException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class-Controller for registration and login.
 */
@AllArgsConstructor
@RestController
public class RegistrationAndLoginController implements IRegistrationAndLoginController {

  private final IUserService userService;
  private final JwtProvider jwtProvider;

  @Override
  @PostMapping(value = "/registration")
  public ResponseEntity<?> addNewUser(@RequestBody UserDto userDto)
      throws EntityAlreadyExistException {
    userService.addNewUser(userDto);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @Override
  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginDto request) {
    User user = userService.findByLoginAndPassword(request.getUsername(), request.getPassword());
    String token = jwtProvider.generateToken(user.getUsername());

    return new ResponseEntity<>(token, HttpStatus.OK);
  }
}
