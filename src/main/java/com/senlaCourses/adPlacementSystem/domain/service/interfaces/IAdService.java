package com.senlaCourses.adPlacementSystem.domain.service.interfaces;

import com.senlaCourses.adPlacementSystem.domain.dto.request.AdDto;
import com.senlaCourses.adPlacementSystem.domain.model.Ad;
import com.senlaCourses.adPlacementSystem.exceptions.EntityAlreadyExistException;
import com.senlaCourses.adPlacementSystem.exceptions.EntityNotFoundException;

/**
 * Interface for AdService realization.
 */
public interface IAdService {

  void addNewAd(AdDto adDto) throws EntityAlreadyExistException, EntityNotFoundException;

  Ad changeAd(long id, AdDto adDto) throws EntityNotFoundException;

  Ad removeAd(long id, boolean isSold) throws EntityNotFoundException;

  boolean removeAdFromDb(long id);

  Ad payForAnAd(long id, boolean isPaid) throws EntityNotFoundException;

  Ad leaveComment(long id, String comment) throws EntityNotFoundException;
}
