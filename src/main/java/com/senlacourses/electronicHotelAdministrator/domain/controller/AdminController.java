package com.senlacourses.electronicHotelAdministrator.domain.controller;

import com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces.IAdminController;
import com.senlacourses.electronicHotelAdministrator.domain.model.User;
import com.senlacourses.electronicHotelAdministrator.domain.service.interfaces.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class AdminController implements IAdminController {

  private final IUserService userService;

  public AdminController(IUserService userService) {
    this.userService = userService;
  }

  @Override
  @Secured({"ROLE_ADMIN"})
  @GetMapping(value = "/admin")
  public ResponseEntity<List<User>> getAllUsers() {
    final List<User> users = userService.getAllUsers();

    return users != null && !users.isEmpty()
            ? new ResponseEntity<>(users, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @Override
  @Secured({"ROLE_ADMIN"})
  @DeleteMapping(value = "/admin")
  public ResponseEntity<?> removeUser(@RequestBody String username) {
    boolean deleted = userService.removeUser(username);

    return deleted
            ? new ResponseEntity<>(HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
  }
}
