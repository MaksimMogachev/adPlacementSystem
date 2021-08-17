package com.senlaCourses.adPlacementSystem.domain.controller.interfaces;

import com.senlaCourses.adPlacementSystem.domain.dto.request.ProfileDto;
import com.senlaCourses.adPlacementSystem.domain.model.Ad;
import com.senlaCourses.adPlacementSystem.domain.model.Profile;
import com.senlaCourses.adPlacementSystem.exceptions.EntityAlreadyExistException;
import com.senlaCourses.adPlacementSystem.exceptions.EntityNotFoundException;
import java.util.Set;
import org.springframework.http.ResponseEntity;

/**
 * Interface for ProfileController realization.
 */
public interface IProfileController {

  ResponseEntity<?> addNewProfile(ProfileDto profileDto) throws EntityAlreadyExistException;

  ResponseEntity<?> removeProfile() throws EntityNotFoundException;

  ResponseEntity<Profile> changePhoneNumber(String newPhoneNumber)
      throws EntityNotFoundException;

  ResponseEntity<Profile> showProfileInformation(long id)
      throws EntityNotFoundException;

  ResponseEntity<Set<Ad>> showHistoryOfAds(long id) throws EntityNotFoundException;
}
