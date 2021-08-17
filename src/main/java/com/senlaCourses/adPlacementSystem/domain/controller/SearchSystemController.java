package com.senlaCourses.adPlacementSystem.domain.controller;

import com.senlaCourses.adPlacementSystem.domain.controller.interfaces.ISearchSystemController;
import com.senlaCourses.adPlacementSystem.domain.dto.request.AdDtoToCustomSearch;
import com.senlaCourses.adPlacementSystem.domain.model.Ad;
import com.senlaCourses.adPlacementSystem.domain.service.interfaces.ISearchSystemService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class-Controller for working with ads search system.
 */
@AllArgsConstructor
@RestController
public class SearchSystemController implements ISearchSystemController {

  private final ISearchSystemService searchSystemService;

  @Override
  @GetMapping(value = "/search")
  public ResponseEntity<List<Ad>> searchAmongAllAds(@RequestParam String nameOfAd) {
    List<Ad> ads = searchSystemService.searchAmongAllAds(nameOfAd);

    return new ResponseEntity<>(ads, HttpStatus.OK);
  }

  @Override
  @GetMapping(value = "/search/custom")
  public ResponseEntity<List<Ad>> customSearchAmongAllAds(
      @RequestBody AdDtoToCustomSearch adDtoToCustomSearch) {
    List<Ad> ads = searchSystemService.customSearchAmongAllAds(adDtoToCustomSearch);

    return new ResponseEntity<>(ads, HttpStatus.OK);
  }
}
