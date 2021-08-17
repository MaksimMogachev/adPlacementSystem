package com.senlaCourses.adPlacementSystem.domain.controller.interfaces;

import com.senlaCourses.adPlacementSystem.domain.dto.request.UserDto;
import com.senlaCourses.adPlacementSystem.exceptions.EntityAlreadyExistException;
import org.springframework.http.ResponseEntity;

/**
 * Interface for RegistrationAndLoginController realization.
 */
public interface IRegistrationAndLoginController {

  ResponseEntity<?> addNewUser(UserDto userDto) throws EntityAlreadyExistException;

  ResponseEntity<?> login(UserDto request);
}
