package com.senlaCourses.adPlacementSystem.dao;

import com.senlaCourses.adPlacementSystem.dao.interfaces.IUserDao;
import com.senlaCourses.adPlacementSystem.domain.model.User;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Class for data access User.class object.
 */
@Repository
public class UserDao extends AbstractHibernateDao<User> implements IUserDao {

  @Autowired
  private EntityManager entityManager;

  public UserDao() {
    super(User.class);
  }

  /**
   * Searches user by email.
   *
   * @param mailTo search mail.
   * @return found user.
   */
  @Override
  public User readByMail(String mailTo) {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);

    Root<User> ad = criteriaQuery.from(User.class);
    Predicate searchMailPredicate = builder.equal(ad.get("email"), mailTo);
    criteriaQuery.where(searchMailPredicate);

    TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
    return query.getSingleResult();
  }
}
