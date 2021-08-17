package com.senlaCourses.adPlacementSystem.domain.controller.interfaces;

import com.senlaCourses.adPlacementSystem.domain.dto.request.UserDtoForChangingThePassword;
import com.senlaCourses.adPlacementSystem.domain.model.User;
import org.springframework.http.ResponseEntity;

/**
 * Interface for UserController realization.
 */
public interface IUserController {

  ResponseEntity<User> changePassword(UserDtoForChangingThePassword userDtoForChangingThePassword);

  ResponseEntity<User> changeEmail(String newEmail);
}
