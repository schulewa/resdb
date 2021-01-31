package com.apschulewitz.resdb.common.service;

import java.util.List;

/**
 *
 * @param <D> is the type of Dto handled by this data service
 * @param <E> is the type of JPA Entity handled by this data service
 * @param <K> is the Key type for the Entity
 */
public interface DataService<D, E, K> {

  D add(D dto);

  D deleteById(K key);

  List<D> findAll(boolean onlyActive);

  D findById(K key);

  D update(D dto);

}
