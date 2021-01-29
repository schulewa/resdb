package com.apschulewitz.resdb.common.utils;

import com.apschulewitz.resdb.common.api.request.ApiOperationType;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Slf4j
public final class LoggingUtils {

  public static final void logData(String entityName, Class clazz, CrudRepository<Class, Long> dao) {
    Stream<Class> stream = StreamSupport.stream(dao.findAll().spliterator(), false);
    stream
      .filter(clazz::isInstance)
      .map(clazz::cast)
      .forEach(e -> log.info("  {}: {}", entityName, e));
  }

  public static void logAddedEntity(EntityTypeEnum entityType, Object entity) {
    log.info(String.format("Added %s entity [%s]", entityType.getType(), entity));
  }

  public static void logStartOfRequest(ApiOperationType operation, EntityTypeEnum entityType, Object toBeSaved) {
    log.info("Processing request to {} {} [{}]", operation.getAction(), entityType.getType(), toBeSaved);
  }

  public static void logStartOfRequest(ApiOperationType operation, EntityTypeEnum entityType) {
    log.info("Processing request to {} {}", operation.getAction(), entityType.getType());
  }

  public static void logEndOfRequest(ApiOperationType operation, EntityTypeEnum entityType, Object toBeSaved) {
    log.info("Processed request to {} {} [{}]", operation.getAction(), entityType.getType(), toBeSaved);
  }

  public static void logEndOfRequest(ApiOperationType operation, EntityTypeEnum entityType) {
    log.info("Processed request to {} {}", operation.getAction(), entityType.getType());
  }

  public static void logStartOfAddRequest(EntityTypeEnum entityType, Object toBeSaved) {
    logStartOfRequest(ApiOperationType.ADD, entityType, toBeSaved);
  }

  public static void logEndOfAddRequest(EntityTypeEnum entityType, Object toBeSaved) {
    logEndOfRequest(ApiOperationType.ADD, entityType, toBeSaved);
  }

  public static void logStartOfDeleteRequest(EntityTypeEnum entityType, Object toBeSaved) {
    logStartOfRequest(ApiOperationType.DELETE, entityType, toBeSaved);
  }

  public static void logEndOfDeleteRequest(EntityTypeEnum entityType, Object toBeSaved) {
    logEndOfRequest(ApiOperationType.DELETE, entityType, toBeSaved);
  }

  public static void logStartOfFindAllRequest(EntityTypeEnum entityType) {
    logStartOfRequest(ApiOperationType.FIND_ALL, entityType);
  }

  public static void logEndOfFindAllRequest(EntityTypeEnum entityType) {
    logEndOfRequest(ApiOperationType.FIND_ALL, entityType);
  }

  public static void logStartOfFindAllActiveRequest(EntityTypeEnum entityType) {
    logStartOfRequest(ApiOperationType.FIND_ALL_ACTIVE, entityType);
  }

  public static void logEndOfFindAllActiveRequest(EntityTypeEnum entityType) {
    logEndOfRequest(ApiOperationType.FIND_ALL_ACTIVE, entityType);
  }

  public static void logStartOfUpdateRequest(EntityTypeEnum entityType, Object toBeSaved) {
    logStartOfRequest(ApiOperationType.UPDATE, entityType, toBeSaved);
  }

  public static void logEndOfUpdateRequest(EntityTypeEnum entityType, Object toBeSaved) {
    logEndOfRequest(ApiOperationType.UPDATE, entityType, toBeSaved);
  }

  public static void logNoEntityFoundForId(EntityTypeEnum entityType, Long id) {
    log.info(String.format("No %s entity found for ID %d", entityType.getType(), id));
  }

//  public static void logError
  public static void logAttemptingToFindEntityForId(EntityTypeEnum entityType, Object id) {
    log.info(String.format("Attempting to find %s with ID %d", entityType.getType(), id));
  }

  public static void logAttemptingToMarkEntityAsDeleted(EntityTypeEnum entityType, Long id) {
    log.info(String.format("Marking %s with ID %d as deleted", entityType.getType(), id));
  }

  public static void logAttemptingToUpdateEntity(EntityTypeEnum entityTypeEnum, Object entity) {
    log.info(String.format("Attempting to update %s [%s]", entityTypeEnum.getType(), entity));
  }

  public static void logUnableToAddEntityWithId(EntityTypeEnum entityType, Object entity) {
    log.info(String.format("Unable to add entity %s as ID %s is already set", entityType.getType(), entity));
  }

  public static void logUnableToMarkInactiveEntityAsDeleted(EntityTypeEnum entityType, Object id) {
    log.info(String.format("Unable to mark inactive %s for ID %d as deleted", entityType.getType(), id));
  }

  public static void logUnableToUpdateEntityWithNoId(EntityTypeEnum entityType, Object entity) {
    log.info(String.format("Unable to update %s entity with no ID [%s]", entityType.getType(), entity));
  }

  public static void logUpdatedEntity(EntityTypeEnum entityType, Object entity) {
    log.info(String.format("Updated %s entity [%s]", entityType.getType(), entity));
  }

  public static void logEntityMarkedAsDeleted(EntityTypeEnum entityType, Long id) {
    log.info(String.format("%s entity with ID %d marked as deleted", entityType.getType(), id));
  }

  public static void logFindingAllEntities(EntityTypeEnum entityType) {
    log.info(String.format("Finding all %s entities", entityType.getType()));
  }

  public static void logFoundEntityForId(EntityTypeEnum entityType, Object id) {
    log.info(String.format("Found %s entity for ID %d", entityType.getType(), id));
  }

  public static void logFoundNoEntityForId(EntityTypeEnum entityType, Object id) {
    log.info(String.format("Found no %s entity for ID %d", entityType.getType(), id));
  }

  public static void logFoundCountEntities(EntityTypeEnum entityType, boolean onlyActive, int count) {
    if (onlyActive)
      logFoundCountActiveEntities(entityType, count);
    else
      logFoundCountAllEntities(entityType, count);
  }

  public static void logFoundCountAllEntities(EntityTypeEnum entityType, int count) {
    log.info(String.format("Found %d %s entity instances", count, entityType.getType()));
  }

  public static void logFoundCountActiveEntities(EntityTypeEnum entityType, int count) {
    log.info(String.format("Found %d active %s entity instances", count, entityType.getType()));
  }

  public static void logUnableToMapNullDto(EntityTypeEnum entityType, Object id) {
    log.info(String.format("Unable to map null %s dto %d", entityType.getType(), id));
  }

  public static void logUnableToMapNullEntity(EntityTypeEnum entityType, Object id) {
    log.info(String.format("Unable to map null %s entity %d", entityType.getType(), id));
  }

  public static void logUnableToMapNullDtoToEntity(EntityTypeEnum entityType) {
    log.info(String.format("Unable to map null %s dto to entity", entityType.getType()));
  }
}
