package com.senlaCourses.adPlacementSystem.exceptions;

/**
 * Exception throws if entity not found id DB.
 */
public class EntityNotFoundException extends Exception {

  public EntityNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
