package com.senlaCourses.adPlacementSystem.domain.service.interfaces;

import com.senlaCourses.adPlacementSystem.domain.dto.request.AdDtoToCustomSearch;
import com.senlaCourses.adPlacementSystem.domain.model.Ad;
import java.util.List;
import javax.transaction.Transactional;

/**
 * Interface for SearchSystemService realization.
 */
public interface ISearchSystemService {

  List<Ad> searchAmongAllAds(String nameOfAd);

  List<Ad> customSearchAmongAllAds(AdDtoToCustomSearch dto);
}
