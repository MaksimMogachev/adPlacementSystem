package com.senlaCourses.adPlacementSystem.domain.service;

import com.senlaCourses.adPlacementSystem.dao.interfaces.IProfileDao;
import com.senlaCourses.adPlacementSystem.dao.interfaces.IUserDao;
import com.senlaCourses.adPlacementSystem.domain.dto.request.ProfileDto;
import com.senlaCourses.adPlacementSystem.domain.model.Ad;
import com.senlaCourses.adPlacementSystem.domain.model.Profile;
import com.senlaCourses.adPlacementSystem.domain.model.User;
import com.senlaCourses.adPlacementSystem.domain.service.interfaces.IProfileService;
import com.senlaCourses.adPlacementSystem.exceptions.EntityAlreadyExistException;
import com.senlaCourses.adPlacementSystem.exceptions.EntityNotFoundException;
import java.util.Set;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Class-Service for working with profile.
 */
@Slf4j
@AllArgsConstructor
@Service
public class ProfileService implements IProfileService {

  private final IProfileDao profileDao;
  private final IUserDao userDao;

  /**
   * Adds new profile in DB.
   *
   * @param profileDto profile data for adding.
   * @throws EntityAlreadyExistException if profile already have ad with this name.
   */
  @Transactional
  @Override
  public void addNewProfile(ProfileDto profileDto) throws EntityAlreadyExistException {
    UserDetails userDetails = (UserDetails) SecurityContextHolder
        .getContext().getAuthentication().getPrincipal();
    User user = userDao.readByNaturalId(userDetails.getUsername());

    if (profileDao.readByNaturalId(user.getUsername()) != null) {
      log.error("EntityAlreadyExistException(\"This profile already exist\")");
      throw new EntityAlreadyExistException("This profile already exist");
    }

    Profile profile = new Profile();
    profile.setUser(user);
    profile.setUsername(user.getUsername());
    profile.setPhoneNumber(profileDto.getPhoneNumber());
    profileDao.create(profile);

    user.setProfile(profile);
    userDao.update(user);
  }

  /**
   * Removes profile from DB.
   *
   * @return profile was deleted or not.
   * @throws EntityNotFoundException if profile wasn't found in DB.
   */
  @Transactional
  @Override
  public boolean removeProfile() throws EntityNotFoundException {
    Profile profile = getCurrentProfile();
    User user = userDao.readByNaturalId(profile.getUsername());

    user.setProfile(null);

    userDao.update(user);
    profileDao.delete(profile);
    return profileDao.read(profile.getId()) == null;
  }

  /**
   * Changes phoneNumber parameter of Profile.class object.
   *
   * @param newPhoneNumber parameter of Profile.class object for change.
   * @return changed Profile.class object.
   * @throws EntityNotFoundException if profile wasn't found in DB.
   */
  @Transactional
  @Override
  public Profile changePhoneNumber(String newPhoneNumber) throws EntityNotFoundException {
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
   * @throws EntityNotFoundException if profile wasn't found in DB.
   */
  @Override
  public Profile showProfileInformation(long id) throws EntityNotFoundException {
    Profile profile = profileDao.read(id);
    if (profile == null) {
      log.error("EntityNotFoundException(\"Profile not found\")");
      throw new EntityNotFoundException("Profile not found");
    }

    return profile;
  }

  /**
   * Shows history of ads of Profile.class object.
   *
   * @param id identifier for search object id DB.
   * @return Set of Ad.class objects.
   * @throws EntityNotFoundException if profile wasn't found in DB.
   */
  @Override
  public Set<Ad> showHistoryOfAds(long id) throws EntityNotFoundException {
    Profile profile = profileDao.read(id);

    if (profile == null) {
      log.error("EntityNotFoundException(\"Profile not found\")");
      throw new EntityNotFoundException("Profile not found");
    }
    return profile.getAds();
  }

  private Profile getCurrentProfile() throws EntityNotFoundException {
    UserDetails userDetails = (UserDetails) SecurityContextHolder
        .getContext().getAuthentication().getPrincipal();
    User user = userDao.readByNaturalId(userDetails.getUsername());

    Profile profile = profileDao.readByNaturalId(user.getUsername());
    if (profile == null) {
      log.error("EntityNotFoundException(\"The user does not have an account to work with ads\")");
      throw new EntityNotFoundException("The user does not have an account to work with ads");
    }
    return profile;
  }
}
