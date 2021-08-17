package com.senlaCourses.adPlacementSystem.dao.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 * Interface for GenericDao realization.
 */
public interface IGenericDao<T extends Serializable> {

  List<T> getAll();

  void create(T t);

  <R> T read(R id);

  <R> T readByNaturalId(R id);

  void update(T t);

  void delete(T t);
}
