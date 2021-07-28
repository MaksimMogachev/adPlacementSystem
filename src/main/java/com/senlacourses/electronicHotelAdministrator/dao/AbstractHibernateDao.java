package com.senlacourses.electronicHotelAdministrator.dao;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;


public abstract class AbstractHibernateDao<T extends Serializable> {

  private final Class<T> clazz;
  @Autowired
  private EntityManager entityManager;

  public AbstractHibernateDao(Class<T> clazz) {
    this.clazz = clazz;
  }

  public <R> T read(R id){
    return (T) entityManager.find(clazz, id);
  }

  public List<T> getAll() {
    return entityManager.createQuery( "from " + clazz.getName() )
            .getResultList();
  }

  public void create(T entity) {
    executeInsideTransaction(entityManager -> entityManager.persist(entity));
  }

  public void update(T entity) {
    executeInsideTransaction(entityManager -> entityManager.merge(entity));
  }

  public void delete(T entity) {
    executeInsideTransaction(entityManager -> entityManager.remove(entity));;
  }

  private void executeInsideTransaction(Consumer<EntityManager> action) {
    EntityTransaction tx = entityManager.getTransaction();
    try {
      tx.begin();
      action.accept(entityManager);
      tx.commit();
    }
    catch (RuntimeException e) {
      tx.rollback();
      throw e;
    }
  }
}
