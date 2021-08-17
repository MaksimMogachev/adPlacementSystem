package com.senlaCourses.adPlacementSystem.domain.service;

import com.senlaCourses.adPlacementSystem.dao.interfaces.IAdDao;
import com.senlaCourses.adPlacementSystem.dao.interfaces.IProfileDao;
import com.senlaCourses.adPlacementSystem.dao.interfaces.IUserDao;
import com.senlaCourses.adPlacementSystem.domain.dto.request.AdDto;
import com.senlaCourses.adPlacementSystem.domain.dto.request.AdDtoToChange;
import com.senlaCourses.adPlacementSystem.domain.dto.request.AdDtoToCustomSearch;
import com.senlaCourses.adPlacementSystem.domain.model.Ad;
import com.senlaCourses.adPlacementSystem.domain.model.CategoryOfAd;
import com.senlaCourses.adPlacementSystem.domain.model.Profile;
import com.senlaCourses.adPlacementSystem.domain.model.User;
import com.senlaCourses.adPlacementSystem.domain.service.interfaces.IAdService;
import com.senlaCourses.adPlacementSystem.exceptions.EntityAlreadyExistException;
import com.senlaCourses.adPlacementSystem.exceptions.EntityNotFoundException;
import java.util.List;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Class-Service for working with ad.
 */
@Slf4j
@AllArgsConstructor
@Service
public class AdService implements IAdService {

  private final IUserDao userDao;
  private final IProfileDao profileDao;
  private final IAdDao adDao;

  /**
   * Adds new ad.
   *
   * @param adDto ad data for adding.
   * @throws EntityAlreadyExistException if user already have ad with this name.
   * @throws EntityNotFoundException if profile wasn't found in DB.
   */
  @Transactional
  @Override
  public void addNewAd(AdDto adDto) throws EntityAlreadyExistException, EntityNotFoundException {
    Profile profile = getCurrentProfile();

    for (Ad ad : profile.getAds()) {
      if (ad.getNameOfAd()
          .equals(adDto.getNameOfAd())) {
        log.error("EntityAlreadyExistException(\"You already have ad with this name\")");
        throw new EntityAlreadyExistException("You already have ad with this name");
      }
    }
    if (adDto.getPrice() < 1) {
      log.error("IllegalArgumentException(\"The price cannot be less than 1\")");
      throw new IllegalArgumentException("The price cannot be less than 1");
    }

    Ad ad = new Ad();
    ad.setNameOfAd(adDto.getNameOfAd());
    ad.setDescription(adDto.getDescription());
    ad.setPrice(adDto.getPrice());
    ad.setCategory(CategoryOfAd
        .valueOf(adDto.getCategory().toUpperCase()));

    profile.getAds().add(ad);
    profileDao.update(profile);
    adDao.create(ad);
  }

  /**
   * Changes ad.
   *
   * @param id identifier for search object id DB.
   * @param adDto ad data for change Ad.class object id DB.
   * @return changed Ad.class object or null if ad not found.
   * @throws EntityNotFoundException if profile wasn't found in DB.
   */
  @Transactional
  @Override
  public Ad changeAd(long id, AdDtoToChange adDto) throws EntityNotFoundException {
    Profile profile = getCurrentProfile();
    Ad ad = adDao.read(id);

    for (Ad adInSet : profile.getAds()) {
      if (adInSet.getId() == id) {
        ad = adInSet;
        break;
      }
    }
    if (ad == null) {
      log.error("EntityNotFoundException(\"Ad not found\")");
      throw new EntityNotFoundException("Ad not found");
    }
    if (!ad.isActive()) {
      log.error("IllegalArgumentException(\"ad is not active\")");
      throw new IllegalArgumentException("ad is not active");
    }

    if (adDto.getNameOfAd() != null) {
      ad.setNameOfAd(adDto.getNameOfAd());
    }
    if (adDto.getDescription() != null) {
      ad.setDescription(adDto.getDescription());
    }
    if (adDto.getPrice() > 0) {
      ad.setPrice(adDto.getPrice());
    }
    if (adDto.getCity() != null) {
      ad.setCity(adDto.getCity());
    }
    if (adDto.getCategory() != null) {
      ad.setCategory(CategoryOfAd
          .valueOf(adDto.getCategory().toUpperCase()));
    }
    adDao.update(ad);
    return ad;
  }

  /**
   * Removes ad.
   *
   * @param id identifier for search object id DB.
   * @param isSold has the item been sold?
   * @return changed Ad.class object.
   * @throws EntityNotFoundException if profile wasn't found in DB.
   */
  @Transactional
  @Override
  public Ad removeAd(long id, boolean isSold) throws EntityNotFoundException {
    Profile profile = getCurrentProfile();
    Ad ad = null;

    for (Ad adInSet : profile.getAds()) {
      if (adInSet.getId() == id) {
        ad = adInSet;
        break;
      }
    }
    if (ad == null) {
      log.error("EntityNotFoundException(\"Ad not found\")");
      throw new EntityNotFoundException("Ad not found");
    }
    if (isSold) {
      profile.setRating(profile.getRating() + 1);
    }

    profileDao.update(profile);
    ad.setActive(false);
    adDao.update(ad);
    return ad;
  }

  /**
   * Removes ad from DB.
   * Method for admins.
   *
   * @param id identifier for search object id DB.
   * @return ad was deleted or not.
   */
  @Transactional
  @Override
  public boolean removeAdFromDb(long id) {
    Ad ad = adDao.read(id);
    if (ad == null) {
      return false;
    }

    adDao.delete(ad);
    return adDao.read(id) == null;
  }

  /**
   * Pays for finding an ad in the top.
   *
   * @param id identifier for search object id DB.
   * @param isPaid payment has been made.
   * @return changed Ad.
   */
  @Transactional
  @Override
  public Ad payForAnAd(long id, boolean isPaid) throws EntityNotFoundException {
    Ad ad = adDao.read(id);
    if (ad == null) {
      log.error("EntityNotFoundException(\"Ad not found\")");
      throw new EntityNotFoundException("Ad not found");
    }

    ad.setPaid(true);
    adDao.update(ad);
    return ad;
  }

  /**
   * Leave a comment under the ad.
   *
   * @param id identifier for search object id DB.
   * @param comment comment that will be left under the ad.
   * @return changed Ad.class object.
   */
  @Transactional
  @Override
  public Ad leaveComment(long id, String comment) throws EntityNotFoundException {
    Profile profile = getCurrentProfile();
    Ad ad = adDao.read(id);

    if (ad == null) {
      log.error("EntityNotFoundException(\"Ad not found\")");
      throw new EntityNotFoundException("Ad not found");
    }

    ad.getComments().add(profile.getUsername() + ": " + comment);
    adDao.update(ad);
    return ad;
  }

  private Profile getCurrentProfile() throws EntityNotFoundException {
    UserDetails userDetails = (UserDetails) SecurityContextHolder
        .getContext().getAuthentication().getPrincipal();
    User user = userDao.readByNaturalId(userDetails.getUsername());

    Profile profile = profileDao.read(user.getUsername());
    if (profile == null) {
      log.error("EntityNotFoundException(\"The user does not have an account to post ads\")");
      throw new EntityNotFoundException("The user does not have an account to post ads");
    }
    return profile;
  }
}
