package com.senlaCourses.adPlacementSystem.dao;

import com.senlaCourses.adPlacementSystem.dao.interfaces.IAdDao;
import com.senlaCourses.adPlacementSystem.domain.dto.request.AdDtoToCustomSearch;
import com.senlaCourses.adPlacementSystem.domain.model.Ad;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

/**
 * Class for data access Ad.class object.
 */
@Repository
public class AdDao extends AbstractHibernateDao<Ad> implements IAdDao {

  public AdDao() {
    super(Ad.class);
  }

  //todo check correct place for methods.
  /**
   * Searches ad among all ads by name.
   *
   * @param nameOfAd search name.
   * @return found list of ads.
   */
  public List<Ad> searchAmongAllAds(String nameOfAd) {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Ad> criteriaQuery = builder.createQuery(Ad.class);

    Root<Ad> ad = criteriaQuery.from(Ad.class);
    Predicate searchStringPredicate = builder.like(ad.get("nameOfAd"), "%" + nameOfAd + "%");
    criteriaQuery.where(searchStringPredicate);

    TypedQuery<Ad> query = entityManager.createQuery(criteriaQuery);
    return query.getResultList();
  }

  /**
   * Searches ad among all ads by criteria.
   *
   * @param dto AdDtoToCustomSearch.class object with criteria for search.
   * @return found list of ads.
   */
  public List<Ad> customSearchAmongAllAds(AdDtoToCustomSearch dto) {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Ad> criteriaQuery = builder.createQuery(Ad.class);
    Predicate searchNameOfAdPredicate;
    Predicate searchMinPricePredicate = null;
    Predicate searchMaxPricePredicate = null;
    Predicate searchCityPredicate = null;
    Predicate searchCategoryPredicate = null;

    Root<Ad> ad = criteriaQuery.from(Ad.class);
    searchNameOfAdPredicate = builder
        .like(ad.get("nameOfAd"), "%" + dto.getNameOfAd() + "%");
    if (dto.getMinPrice() > 0) {
      searchMinPricePredicate = builder.greaterThanOrEqualTo(ad.get("price"), dto.getMinPrice());
    }
    if (dto.getMaxPrice() > 0) {
      searchMaxPricePredicate = builder.lessThanOrEqualTo(ad.get("price"), dto.getMaxPrice());
    }
    if (dto.getCity() != null) {
      searchCityPredicate = builder.equal(ad.get("city"), dto.getCity());
    }
    if (dto.getCategory() != null) {
      searchCategoryPredicate = builder.equal(ad.get("category"), dto.getCategory());
    }

    criteriaQuery.where(searchNameOfAdPredicate,
                        searchMinPricePredicate,
                        searchMaxPricePredicate,
                        searchCityPredicate,
                        searchCategoryPredicate);

    TypedQuery<Ad> query = entityManager.createQuery(criteriaQuery);
    return query.getResultList();
  }
}
