package com.senlaCourses.adPlacementSystem.domain.service;

import com.senlaCourses.adPlacementSystem.dao.AdDao;
import com.senlaCourses.adPlacementSystem.dao.ProfileDao;
import com.senlaCourses.adPlacementSystem.domain.dto.request.AdDto;
import com.senlaCourses.adPlacementSystem.domain.dto.request.AdDtoToChange;
import com.senlaCourses.adPlacementSystem.domain.model.Ad;
import com.senlaCourses.adPlacementSystem.domain.model.CategoryOfAd;
import com.senlaCourses.adPlacementSystem.domain.model.Profile;
import com.senlaCourses.adPlacementSystem.domain.model.User;
import com.senlaCourses.adPlacementSystem.domain.service.interfaces.IAdService;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Class-Service for working with ad.
 */
public class AdService implements IAdService {

  private final ProfileDao profileDao;
  private final AdDao adDao;

  public AdService(ProfileDao profileDao, AdDao adDao) {
    this.profileDao = profileDao;
    this.adDao = adDao;
  }

  /**
   * Adds new ad.
   *
   * @param adDto ad data for adding.
   */
  @Override
  public void addNewAd(AdDto adDto) {
    Profile profile = getCurrentProfile();

    for (Ad ad : profile.getAds()) {
      if (ad.getNameOfAd()
          .equals(adDto.getNameOfAd())) {
        throw new IllegalArgumentException("You already have ad with this name");
      }
    }
    if (adDto.getPrice() < 1) {
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
   * @param adDto ad data for changing Ad.class object id DB.
   * @return changed Ad.class object or null if ad not found.
   */
  @Override
  public Ad changeAd(long id, AdDtoToChange adDto) {
    Profile profile = getCurrentProfile();
    Ad ad = null;

    for (Ad adInSet : profile.getAds()) {
      if (adInSet.getId() == id) {
        ad = adInSet;
        break;
      }
    }
    if (ad == null) {
      return null;
    }
    if (!ad.isActive()) {
      throw new NullPointerException("ad is not active");
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
   */
  @Override
  public Ad removeAd(long id, boolean isSold) {
    Profile profile = getCurrentProfile();
    Ad ad = null;

    for (Ad adInSet : profile.getAds()) {
      if (adInSet.getId() == id) {
        ad = adInSet;
        break;
      }
    }
    if (ad == null) {
      return null;
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
   * @param isPaid payment has been made.
   * @param id identifier for search object id DB.
   * @return changed Ad.
   */
  @Override
  public Ad payForAnAd(boolean isPaid, long id) {
    Ad ad = adDao.read(id);
    if (ad == null) {
      return null;
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
  @Override
  public Ad leaveComment(long id, String comment) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (user == null) {
      throw new NullPointerException("User not logged in");
    }

    Ad ad = adDao.read(id);

    if (ad == null) {
      return null;
    }

    ad.getComments().add(user.getUsername() + ": " + comment);
    adDao.update(ad);
    return ad;
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
