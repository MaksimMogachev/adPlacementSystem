package com.senlaCourses.adPlacementSystem.dao.interfaces;

import com.senlaCourses.adPlacementSystem.domain.dto.request.AdDtoToCustomSearch;
import com.senlaCourses.adPlacementSystem.domain.model.Ad;
import java.util.List;

/**
 * Interface for AnnouncementDao realization.
 */
public interface IAdDao extends IGenericDao<Ad> {

  List<Ad> searchAmongAllAds(String nameOfAd);

  List<Ad> customSearchAmongAllAds(AdDtoToCustomSearch dto);
}
