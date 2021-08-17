package com.senlaCourses.adPlacementSystem.domain.controller.interfaces;

import com.senlaCourses.adPlacementSystem.domain.dto.request.AdDtoToCustomSearch;
import com.senlaCourses.adPlacementSystem.domain.model.Ad;
import java.util.List;
import org.springframework.http.ResponseEntity;

/**
 * Interface for SearchSystemController realization.
 */
public interface ISearchSystemController {

  ResponseEntity<List<Ad>> searchAmongAllAds(String nameOfAd);

  ResponseEntity<List<Ad>> customSearchAmongAllAds(AdDtoToCustomSearch adDtoToCustomSearch);
}
