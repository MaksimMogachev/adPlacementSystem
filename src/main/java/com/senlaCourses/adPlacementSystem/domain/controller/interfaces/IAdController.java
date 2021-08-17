package com.senlaCourses.adPlacementSystem.domain.controller.interfaces;

import com.senlaCourses.adPlacementSystem.domain.dto.request.AdDto;
import com.senlaCourses.adPlacementSystem.domain.dto.request.AdDtoToChange;
import com.senlaCourses.adPlacementSystem.domain.model.Ad;
import com.senlaCourses.adPlacementSystem.exceptions.EntityAlreadyExistException;
import com.senlaCourses.adPlacementSystem.exceptions.EntityNotFoundException;
import org.springframework.http.ResponseEntity;

/**
 * Interface for AdController realization.
 */
public interface IAdController {

  ResponseEntity<?> addNewAd(AdDto adDto)
      throws EntityAlreadyExistException, EntityNotFoundException;

  ResponseEntity<Ad> changeAd(long id, AdDtoToChange adDtoToChange) throws EntityNotFoundException;

  ResponseEntity<Ad> removeAd(long id, boolean isSold) throws EntityNotFoundException;

  ResponseEntity<Ad> payForAnAd(long id, boolean isPaid) throws EntityNotFoundException;

  ResponseEntity<Ad> leaveComment(long id, String comment) throws EntityNotFoundException;
}
