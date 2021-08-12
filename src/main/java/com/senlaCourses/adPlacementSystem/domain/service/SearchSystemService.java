package com.senlaCourses.adPlacementSystem.domain.service;

import com.senlaCourses.adPlacementSystem.dao.AdDao;
import com.senlaCourses.adPlacementSystem.domain.dto.request.AdDtoToCustomSearch;
import com.senlaCourses.adPlacementSystem.domain.model.Ad;
import com.senlaCourses.adPlacementSystem.domain.service.interfaces.ISearchSystemService;
import java.util.List;

/**
 * Class-Service for handling the search.
 */
public class SearchSystemService implements ISearchSystemService {

  private final AdDao adDao;

  public SearchSystemService(AdDao adDao) {
    this.adDao = adDao;
  }

  /**
   * Searches ad among all ads by name.
   *
   * @param nameOfAd search name.
   * @return found list of ads.
   */
  public List<Ad> searchAmongAllAds(String nameOfAd) {
    return null;
  }

  /**
   * Searches ad among all ads by criteria.
   *
   * @param dto AdDtoToCustomSearch.class object with criteria for search.
   * @return found list of ads.
   */
  public List<Ad> customSearchAmongAllAds(AdDtoToCustomSearch dto) {
    return null;
  }
}
