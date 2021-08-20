package com.senlaCourses.adPlacementSystem.dao;

import com.senlaCourses.adPlacementSystem.dao.interfaces.IAdDao;
import com.senlaCourses.adPlacementSystem.domain.dto.request.AdDtoToCustomSearch;
import com.senlaCourses.adPlacementSystem.domain.model.Ad;
import com.senlaCourses.adPlacementSystem.domain.model.CategoryOfAd;
import java.util.LinkedList;
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
    Predicate searchActivePredicate = builder.isTrue(ad.get("isActive"));
    Predicate searchNameOfAdPredicate = builder.like(ad.get("nameOfAd"), "%" + nameOfAd + "%");
    criteriaQuery.where(searchActivePredicate, searchNameOfAdPredicate);

    TypedQuery<Ad> query = entityManager.createQuery(criteriaQuery);
    return query.getResultList();
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
    List<Predicate> predicateList = new LinkedList<>();
    Predicate searchNameOfAdPredicate;
    Predicate searchActivePredicate;
    Predicate searchMinPricePredicate;
    Predicate searchMaxPricePredicate;
    Predicate searchCityPredicate;
    Predicate searchCategoryPredicate;

    Root<Ad> ad = criteriaQuery.from(Ad.class);
    searchActivePredicate = builder.isTrue(ad.get("isActive"));
    predicateList.add(searchActivePredicate);
    searchNameOfAdPredicate = builder
        .like(ad.get("nameOfAd"), "%" + dto.getNameOfAd() + "%");
    predicateList.add(searchNameOfAdPredicate);
    if (dto.getMinPrice() > 0) {
      searchMinPricePredicate = builder.greaterThanOrEqualTo(ad.get("price"), dto.getMinPrice());
      predicateList.add(searchMinPricePredicate);
    }
    if (dto.getMaxPrice() > 0) {
      searchMaxPricePredicate = builder.lessThanOrEqualTo(ad.get("price"), dto.getMaxPrice());
      predicateList.add(searchMaxPricePredicate);
    }
    if (dto.getCity() != null) {
      searchCityPredicate = builder.equal(ad.get("city"), dto.getCity());
      predicateList.add(searchCityPredicate);
    }
    if (dto.getCategory() != null) {
      searchCategoryPredicate = builder.equal(ad.get("category"),
                                CategoryOfAd.valueOf(dto.getCategory()));
      predicateList.add(searchCategoryPredicate);
    }

    criteriaQuery.where(predicateList.toArray(new Predicate[0]));

    TypedQuery<Ad> query = entityManager.createQuery(criteriaQuery);
    return query.getResultList();
  }
}
