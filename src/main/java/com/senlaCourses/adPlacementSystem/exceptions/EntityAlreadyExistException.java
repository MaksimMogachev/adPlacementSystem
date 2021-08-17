package com.senlaCourses.adPlacementSystem.exceptions;

/**
 * Exception throws if entity already exist id DB.
 */
public class EntityAlreadyExistException extends Exception {

  public EntityAlreadyExistException(String errorMessage) {
    super(errorMessage);
  }
}
