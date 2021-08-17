package com.senlaCourses.adPlacementSystem.domain.controller;

import com.senlaCourses.adPlacementSystem.domain.controller.interfaces.IUserController;
import com.senlaCourses.adPlacementSystem.domain.dto.request.UserDtoForChangingThePassword;
import com.senlaCourses.adPlacementSystem.domain.model.User;
import com.senlaCourses.adPlacementSystem.domain.service.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class-Controller for working with users.
 */
@AllArgsConstructor
@RestController
public class UserController implements IUserController {

  private final IUserService userService;

  @PutMapping(value = "/user/password")
  @Override
  public ResponseEntity<User> changePassword(
      UserDtoForChangingThePassword userDtoForChangingThePassword) {
    User user = userService.changePassword(userDtoForChangingThePassword);

    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @PutMapping(value = "/user/email")
  @Override
  public ResponseEntity<User> changeEmail(String newEmail) {
    User user = userService.changeEmail(newEmail);

    return new ResponseEntity<>(user, HttpStatus.OK);
  }
}
