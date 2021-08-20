package com.senlaCourses.adPlacementSystem.domain.controller;

import com.senlaCourses.adPlacementSystem.domain.controller.interfaces.IAdController;
import com.senlaCourses.adPlacementSystem.domain.dto.request.AdDto;
import com.senlaCourses.adPlacementSystem.domain.model.Ad;
import com.senlaCourses.adPlacementSystem.domain.service.interfaces.IAdService;
import com.senlaCourses.adPlacementSystem.exceptions.EntityAlreadyExistException;
import com.senlaCourses.adPlacementSystem.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class-Controller for working with ads.
 */
@AllArgsConstructor
@RestController
public class AdController implements IAdController {

  private final IAdService adService;

  @Override
  @PostMapping(value = "/ads")
  public ResponseEntity<?> addNewAd(@RequestBody AdDto adDto)
      throws EntityAlreadyExistException, EntityNotFoundException {
    adService.addNewAd(adDto);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @Override
  @PutMapping(value = "/ads/change")
  public ResponseEntity<Ad> changeAd(@RequestParam long id,
                                     @RequestBody AdDto adDto)
      throws EntityNotFoundException {
    Ad ad = adService.changeAd(id, adDto);

    return new ResponseEntity<>(ad, HttpStatus.OK);
  }

  @Override
  @PutMapping(value = "/ads/remove")
  public ResponseEntity<Ad> removeAd(@RequestParam long id,
                                     @RequestParam boolean isSold) throws EntityNotFoundException {
    Ad ad = adService.removeAd(id, isSold);

    return new ResponseEntity<>(ad, HttpStatus.OK);
  }

  @Override
  @PutMapping(value = "/ads/pay")
  public ResponseEntity<Ad> payForAnAd(@RequestParam long id,
                                       @RequestParam boolean isPaid)
      throws EntityNotFoundException {
    Ad ad = adService.payForAnAd(id, isPaid);

    return new ResponseEntity<>(ad, HttpStatus.OK);
  }

  @Override
  @PutMapping(value = "/ads/comment")
  public ResponseEntity<Ad> leaveComment(@RequestParam long id,
                                         @RequestBody String comment)
      throws EntityNotFoundException {
    Ad ad = adService.leaveComment(id, comment);

    return new ResponseEntity<>(ad, HttpStatus.OK);
  }
}
