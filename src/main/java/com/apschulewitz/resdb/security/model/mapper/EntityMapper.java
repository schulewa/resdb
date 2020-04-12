package com.apschulewitz.resdb.security.model.mapper;

/**
 *
 * @param <E> represents the JPA database entity
 * @param <T> represents the DTO transfer object
 */
public interface EntityMapper<E, T> {

    T toDto(E entity);

    T toDto(E entity, boolean onlyActive);

}
