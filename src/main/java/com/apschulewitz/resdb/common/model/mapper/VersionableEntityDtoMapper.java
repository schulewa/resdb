package com.apschulewitz.resdb.common.model.mapper;

/**
 *
 * @param <E> represents the JPA database entity
 * @param <T> represents the DTO transfer object
 */
public interface VersionableEntityDtoMapper<E, T> extends EntityMapper<E, T> {

  T toDto(E entity, boolean onlyActive);

  E toEntity(T dto, boolean onlyActive);

  default T toDto(E entity) {
    return toDto(entity, true);
  }

  default E toEntity(T dto) {
    return toEntity(dto, true);
  }
}
