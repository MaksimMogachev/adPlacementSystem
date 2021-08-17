package com.senlaCourses.adPlacementSystem.dao;

import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Abstract class for data access objects.
 */
public abstract class AbstractHibernateDao<T extends Serializable> {

  private final Class<T> clazz;
  @Autowired
  private EntityManager entityManager;

  protected AbstractHibernateDao(Class<T> clazz) {
    this.clazz = clazz;
  }

  /**
   * Gets all objects from DB.
   *
   * @return list of objects from DB.
   */
  public List<T> getAll() {
    return entityManager.createQuery("from " + clazz.getName()).getResultList();
  }

  /**
   * Creates new entity in DB.
   *
   * @param entity the object to be placed in the DB.
   */
  public void create(T entity) {
    executeInsideTransaction(entityManager -> entityManager.persist(entity));
  }

  /**
   * Gets object from DB.
   *
   * @param id search parameter.
   * @param <R> type of id.
   * @return found entity.
   */
  public <R> T read(R id) {
    return entityManager.find(clazz, id);
  }

  public <R> T readByNaturalId(R id) {
    return entityManager.unwrap(Session.class).bySimpleNaturalId(clazz).load(id);
  }

  /**
   * Updates entity in DB.
   *
   * @param entity the object to be updated in DB.
   */
  public void update(T entity) {
    executeInsideTransaction(entityManager -> entityManager.merge(entity));
  }

  /**
   * Deletes entity in DB.
   *
   * @param entity the object to be deleted in DB.
   */
  public void delete(T entity) {
    executeInsideTransaction(entityManager -> entityManager.remove(entity));
  }

  /**
   * Processes the transaction.
   *
   * @param action action processed by DB.
   */
  private void executeInsideTransaction(Consumer<EntityManager> action) {
    EntityTransaction transaction = entityManager.getTransaction();
    try {
      transaction.begin();
      action.accept(entityManager);
      transaction.commit();
    } catch (RuntimeException e) {
      transaction.rollback();
      throw e;
    }
  }
}
