package com.senlaCourses.adPlacementSystem.domain.controller;

import com.senlaCourses.adPlacementSystem.domain.controller.interfaces.IProfileController;
import com.senlaCourses.adPlacementSystem.domain.dto.request.ProfileDto;
import com.senlaCourses.adPlacementSystem.domain.model.Ad;
import com.senlaCourses.adPlacementSystem.domain.model.Profile;
import com.senlaCourses.adPlacementSystem.domain.service.interfaces.IProfileService;
import com.senlaCourses.adPlacementSystem.exceptions.EntityAlreadyExistException;
import com.senlaCourses.adPlacementSystem.exceptions.EntityNotFoundException;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class-Controller for working with profiles.
 */
@AllArgsConstructor
@RestController
public class ProfileController implements IProfileController {

  IProfileService profileService;

  @Override
  @PostMapping(value = "/profile")
  public ResponseEntity<?> addNewProfile(@RequestBody ProfileDto profileDto)
      throws EntityAlreadyExistException {
    profileService.addNewProfile(profileDto);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @Override
  @DeleteMapping(value = "/profile")
  public ResponseEntity<?> removeProfile() throws EntityNotFoundException {
    boolean deleted = profileService.removeProfile();

    return deleted
        ? new ResponseEntity<>(HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
  }

  @Override
  @PutMapping(value = "/profile")
  public ResponseEntity<Profile> changePhoneNumber(@RequestBody String newPhoneNumber)
      throws EntityNotFoundException {
    Profile profile = profileService.changePhoneNumber(newPhoneNumber);

    return new ResponseEntity<>(profile, HttpStatus.OK);
  }

  @Override
  @GetMapping(value = "/profile")
  public ResponseEntity<Profile> showProfileInformation(@RequestParam long id)
      throws EntityNotFoundException {
    Profile profile = profileService.showProfileInformation(id);

    return new ResponseEntity<>(profile, HttpStatus.OK);
  }

  @Override
  @GetMapping(value = "/profile/history")
  public ResponseEntity<Set<Ad>> showHistoryOfAds(@RequestParam long id)
      throws EntityNotFoundException {
    Set<Ad> ads = profileService.showHistoryOfAds(id);

    return new ResponseEntity<>(ads, HttpStatus.OK);
  }
}
