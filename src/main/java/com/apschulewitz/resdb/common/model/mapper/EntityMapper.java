package com.apschulewitz.resdb.common.model.mapper;

/**
 *
 * @param <E> represents the JPA database entity
 * @param <T> represents the DTO transfer object
 */
public interface EntityMapper<E, T> {

    T toDto(E entity);

    E toEntity(T dto);
}
