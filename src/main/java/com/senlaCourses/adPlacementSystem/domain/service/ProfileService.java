package com.senlaCourses.adPlacementSystem.domain.service;

import com.senlaCourses.adPlacementSystem.dao.ProfileDao;
import com.senlaCourses.adPlacementSystem.domain.dto.request.ProfileDto;
import com.senlaCourses.adPlacementSystem.domain.model.Ad;
import com.senlaCourses.adPlacementSystem.domain.model.Profile;
import com.senlaCourses.adPlacementSystem.domain.model.User;
import com.senlaCourses.adPlacementSystem.domain.service.interfaces.IProfileService;
import java.util.Set;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Class-Service for working with profile.
 */
public class ProfileService implements IProfileService {

  private final ProfileDao profileDao;

  public ProfileService(ProfileDao profileDao) {
    this.profileDao = profileDao;
  }

  /**
   * Adds new profile in DB.
   *
   * @param profileDto profile data for adding.
   */
  @Override
  public void addNewProfile(ProfileDto profileDto) {
    //TODO check returned user. If doesn't work, change to (UserDetails) and get User from by Id.
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    if (user == null) {
      throw new NullPointerException("User not logged in");
    }
    if (profileDao.read(user.getUsername()) != null) {
      throw new IllegalArgumentException("This profile already exist");
    }

    Profile profile = new Profile();
    profile.setUser(user);
    profile.setUsername(user.getUsername());
    profile.setPhoneNumber(profileDto.getPhoneNumber());

    profileDao.create(profile);
  }

  /**
   * Removes profile from DB.
   *
   * @return profile was deleted or not.
   */
  @Override
  public boolean removeProfile() {
    Profile profile = getCurrentProfile();

    profileDao.delete(profile);
    return profileDao.read(profile.getId()) == null;
  }

  /**
   * Changes phoneNumber parameter of Profile.class object.
   *
   * @param newPhoneNumber parameter of Profile.class object for change.
   * @return changed Profile.class object.
   */
  @Override
  public Profile changePhoneNumber(String newPhoneNumber) {
    Profile profile = getCurrentProfile();

    profile.setPhoneNumber(newPhoneNumber);
    profileDao.update(profile);
    return profile;
  }

  /**
   * Shows Profile.class object information.
   *
   * @param id identifier for search object id DB.
   * @return Profile.class object.
   */
  @Override
  public Profile showProfileInformation(long id) {
    Profile profile = profileDao.read(id);
    if (profile == null) {
      throw new IllegalArgumentException("Profile not found");
    }

    return profile;
  }

  /**
   * Shows history of ads of Profile.class object.
   *
   * @param id identifier for search object id DB.
   * @return Set of Ad.class objects.
   */
  @Override
  public Set<Ad> showHistoryOfAds(long id) {
    Profile profile = profileDao.read(id);
    if (profile == null) {
      throw new IllegalArgumentException("Profile not found");
    }

    return profile.getAds();
  }

  /**
   * Gets profile of current user logged in.
   *
   * @return Profile.class object of current user
   */
  private Profile getCurrentProfile() {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (user == null) {
      throw new NullPointerException("User not logged in");
    }

    Profile profile = profileDao.read(user.getUsername());
    if (profile == null) {
      throw new NullPointerException("The user does not have an account to post ads");
    }
    return profile;
  }
}
