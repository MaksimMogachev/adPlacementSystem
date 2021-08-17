package com.senlaCourses.adPlacementSystem.domain.controller.interfaces;

import com.senlaCourses.adPlacementSystem.exceptions.EntityNotFoundException;
import org.springframework.http.ResponseEntity;

/**
 * Interface for AdminController realization.
 */
public interface IAdminController {

  ResponseEntity<?> removeAdFromDb(long id);

  ResponseEntity<?> removeUser(long id) throws EntityNotFoundException;
}
