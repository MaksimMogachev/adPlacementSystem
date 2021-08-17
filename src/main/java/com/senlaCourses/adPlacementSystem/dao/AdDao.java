package com.senlaCourses.adPlacementSystem.dao;

import com.senlaCourses.adPlacementSystem.dao.interfaces.IAdDao;
import com.senlaCourses.adPlacementSystem.domain.dto.request.AdDtoToCustomSearch;
import com.senlaCourses.adPlacementSystem.domain.model.Ad;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Class for data access Ad.class object.
 */
@Repository
public class AdDao extends AbstractHibernateDao<Ad> implements IAdDao {

  @Autowired
  private EntityManager entityManager;

  public AdDao() {
    super(Ad.class);
  }

  //todo check correct place for methods.
  /**
   * Searches ad among all active ads by name.
   *
   * @param nameOfAd search name.
   * @return found list of ads.
   */
  @Override
  public List<Ad> searchAmongAllAds(String nameOfAd) {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Ad> criteriaQuery = builder.createQuery(Ad.class);

    Root<Ad> ad = criteriaQuery.from(Ad.class);
    Predicate searchActivePredicate = builder.isTrue(ad.get("isOpen"));
    Predicate searchNameOfAdPredicate = builder.like(ad.get("nameOfAd"), "%" + nameOfAd + "%");
    criteriaQuery.where(searchActivePredicate, searchNameOfAdPredicate);

    TypedQuery<Ad> query = entityManager.createQuery(criteriaQuery);
    return getSortedList(query.getResultList());
  }

  /**
   * Searches ad among all active ads by criteria.
   *
   * @param dto AdDtoToCustomSearch.class object with criteria for search.
   * @return found list of ads.
   */
  @Override
  public List<Ad> customSearchAmongAllAds(AdDtoToCustomSearch dto) {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Ad> criteriaQuery = builder.createQuery(Ad.class);
    Predicate searchNameOfAdPredicate;
    Predicate searchActivePredicate;
    Predicate searchMinPricePredicate = null;
    Predicate searchMaxPricePredicate = null;
    Predicate searchCityPredicate = null;
    Predicate searchCategoryPredicate = null;

    Root<Ad> ad = criteriaQuery.from(Ad.class);
    searchActivePredicate = builder.isTrue(ad.get("isOpen"));
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

    criteriaQuery.where(searchActivePredicate,
                        searchNameOfAdPredicate,
                        searchMinPricePredicate,
                        searchMaxPricePredicate,
                        searchCityPredicate,
                        searchCategoryPredicate);

    TypedQuery<Ad> query = entityManager.createQuery(criteriaQuery);
    return getSortedList(query.getResultList());
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
    return ads;
  }
}
