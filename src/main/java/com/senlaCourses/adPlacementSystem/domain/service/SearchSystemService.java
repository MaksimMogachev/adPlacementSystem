package com.senlaCourses.adPlacementSystem.domain.service;

import com.senlaCourses.adPlacementSystem.dao.interfaces.IAdDao;
import com.senlaCourses.adPlacementSystem.domain.dto.request.AdDtoToCustomSearch;
import com.senlaCourses.adPlacementSystem.domain.model.Ad;
import com.senlaCourses.adPlacementSystem.domain.service.interfaces.ISearchSystemService;
import java.util.List;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Class-Service for searching ads.
 */
@AllArgsConstructor
@Service
public class SearchSystemService implements ISearchSystemService {

  private final IAdDao adDao;

  @Transactional
  @Override
  public List<Ad> searchAmongAllAds(String nameOfAd) {
    return adDao.searchAmongAllAds(nameOfAd);
  }

  @Transactional
  @Override
  public List<Ad> customSearchAmongAllAds(AdDtoToCustomSearch dto) {
    return adDao.customSearchAmongAllAds(dto);
  }
}
