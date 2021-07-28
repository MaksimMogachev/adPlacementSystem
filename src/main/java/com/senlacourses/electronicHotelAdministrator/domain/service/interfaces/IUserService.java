package com.senlacourses.electronicHotelAdministrator.domain.service.interfaces;

import com.senlacourses.electronicHotelAdministrator.domain.dto.request.UserDto;
import com.senlacourses.electronicHotelAdministrator.domain.model.User;

import java.util.List;

public interface IUserService {

  List<User> getAllUsers();

  void addNewUser(UserDto user);

  boolean removeUser(String username);

  User findByLoginAndPassword(String username, String password);
}
