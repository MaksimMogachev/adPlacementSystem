package com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces;

import com.senlacourses.electronicHotelAdministrator.domain.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAdminController {

  ResponseEntity<List<User>> getAllUsers();

  ResponseEntity<?> removeUser(String username);
}
