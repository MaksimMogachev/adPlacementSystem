package com.senlaCourses.adPlacementSystem.domain.controller;

import com.senlaCourses.adPlacementSystem.exceptions.EntityAlreadyExistException;
import com.senlaCourses.adPlacementSystem.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Spring controller advice.
 */
@RestControllerAdvice
public class ControllerAdvice {

  @ExceptionHandler(EntityAlreadyExistException.class)
  public ResponseEntity<?> handleEntityAlreadyExistException(EntityAlreadyExistException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<?> handleUsernameNotFoundException(UsernameNotFoundException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
