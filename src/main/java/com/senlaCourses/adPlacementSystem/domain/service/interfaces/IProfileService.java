package com.senlaCourses.adPlacementSystem.domain.service.interfaces;

import com.senlaCourses.adPlacementSystem.domain.dto.request.ProfileDto;
import com.senlaCourses.adPlacementSystem.domain.model.Ad;
import com.senlaCourses.adPlacementSystem.domain.model.Profile;
import java.util.Set;

/**
 * Interface for ProfileService realization.
 */
public interface IProfileService {

  void addNewProfile(ProfileDto profileDto);

  boolean removeProfile();

  Profile changePhoneNumber(String newPhoneNumber);

  Profile showProfileInformation(long id);

  Set<Ad> showHistoryOfAds(long id);
}
