package com.senlaCourses.adPlacementSystem.domain.controller;

import com.senlaCourses.adPlacementSystem.domain.controller.interfaces.IAdminController;
import com.senlaCourses.adPlacementSystem.domain.service.interfaces.IAdService;
import com.senlaCourses.adPlacementSystem.domain.service.interfaces.IUserService;
import com.senlaCourses.adPlacementSystem.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class-Controller for admins working.
 */
@AllArgsConstructor
@RestController
public class AdminController implements IAdminController {

  private final IAdService adService;
  private final IUserService userService;

  @Override
  @DeleteMapping(value = "/admin/remove/ad")
  public ResponseEntity<?> removeAdFromDb(@RequestParam long id) {
    boolean deleted = adService.removeAdFromDb(id);

    return deleted
        ? new ResponseEntity<>(HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
  }

  @Override
  @DeleteMapping(value = "/admin/remove/user")
  public ResponseEntity<?> removeUser(@RequestParam long id) throws EntityNotFoundException {
    boolean deleted = userService.removeUser(id);

    return deleted
        ? new ResponseEntity<>(HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
  }
}
