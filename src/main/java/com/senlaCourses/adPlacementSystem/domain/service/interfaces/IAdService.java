package com.senlaCourses.adPlacementSystem.domain.service.interfaces;

import com.senlaCourses.adPlacementSystem.domain.dto.request.AdDto;
import com.senlaCourses.adPlacementSystem.domain.dto.request.AdDtoToChange;
import com.senlaCourses.adPlacementSystem.domain.model.Ad;

/**
 * Interface for AdService realization.
 */
public interface IAdService {

  void addNewAd(AdDto adDto);

  Ad changeAd(long id, AdDtoToChange adDto);

  Ad removeAd(long id, boolean isSold);

  boolean removeAdFromDb(long id);

  Ad payForAnAd(boolean isPaid, long id);

  Ad leaveComment(long id, String comment);
}
