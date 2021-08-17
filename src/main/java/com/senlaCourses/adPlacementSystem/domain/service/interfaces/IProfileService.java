package com.senlaCourses.adPlacementSystem.domain.service.interfaces;

import com.senlaCourses.adPlacementSystem.domain.dto.request.ProfileDto;
import com.senlaCourses.adPlacementSystem.domain.model.Ad;
import com.senlaCourses.adPlacementSystem.domain.model.Profile;
import com.senlaCourses.adPlacementSystem.exceptions.EntityAlreadyExistException;
import com.senlaCourses.adPlacementSystem.exceptions.EntityNotFoundException;
import java.util.Set;

/**
 * Interface for ProfileService realization.
 */
public interface IProfileService {

  void addNewProfile(ProfileDto profileDto) throws EntityAlreadyExistException;

  boolean removeProfile() throws EntityNotFoundException;

  Profile changePhoneNumber(String newPhoneNumber) throws EntityNotFoundException;

  Profile showProfileInformation(long id) throws EntityNotFoundException;

  Set<Ad> showHistoryOfAds(long id) throws EntityNotFoundException;
}
