package com.senlaCourses.adPlacementSystem.domain.service;

import com.senlaCourses.adPlacementSystem.dao.interfaces.IAdDao;
import com.senlaCourses.adPlacementSystem.domain.dto.request.AdDtoToCustomSearch;
import com.senlaCourses.adPlacementSystem.domain.model.Ad;
import com.senlaCourses.adPlacementSystem.domain.service.interfaces.ISearchSystemService;
import java.util.Collections;
import java.util.List;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Class-Service for searching ads.
 */
@Slf4j
@AllArgsConstructor
@Service
public class SearchSystemService implements ISearchSystemService {

  private final IAdDao adDao;

  @Transactional
  @Override
  public List<Ad> searchAmongAllAds(String nameOfAd) {
    if (nameOfAd == null || nameOfAd.isEmpty()) {
      log.error("IllegalArgumentException(\"Name of ad is empty.\")");
      throw new IllegalArgumentException("Name of ad is empty.");
    }
    return getSortedList(adDao.searchAmongAllAds(nameOfAd));
  }

  @Transactional
  @Override
  public List<Ad> customSearchAmongAllAds(AdDtoToCustomSearch dto) {
    if (dto.getNameOfAd() == null || dto.getNameOfAd().isEmpty()) {
      log.error("IllegalArgumentException(\"Name of ad is empty.\")");
      throw new IllegalArgumentException("Name of ad is empty.");
    }
    return getSortedList(adDao.customSearchAmongAllAds(dto));
  }

  private List<Ad> getSortedList(List<Ad> ads) {
    ads.sort((o1, o2) -> {
      if (o1.isPaid() && !o2.isPaid()) {
        return 1;
      }
      if (!o1.isPaid() && o2.isPaid()) {
        return -1;
      }
      return Integer.compare(o1.getProfile().getRating(), o2.getProfile().getRating());
    });
    Collections.reverse(ads);
    return ads;
  }
}
