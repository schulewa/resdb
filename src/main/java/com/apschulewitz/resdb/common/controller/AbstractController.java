package com.apschulewitz.resdb.common.controller;

import com.apschulewitz.resdb.common.ApplicationResponse;
import com.apschulewitz.resdb.common.api.request.ApiOperationType;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.refdata.model.dto.PersonDto;
import com.apschulewitz.resdb.refdata.model.entity.Country;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

/**
 * Created by adrianschulewitz on 15/05/2017.
 */
@Slf4j
public abstract class AbstractController<T, K extends Serializable> {

//    public JSONObject findAll(CrudRepository<T, K> dao) {
//        log.info("Processing request to find all entries");
//        ApplicationResponse<T> applicationResponse = new ApplicationResponse<>();
//        List<T> data = new ArrayList<>();
//        Iterable<T> iter = dao.findAll();
//        StreamSupport.stream(iter.spliterator(), false)
//                .forEach(e -> data.add(e));
//        applicationResponse.setData(data);
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("response", applicationResponse);
//        return jsonObject;
//    }

  public abstract ResponseEntity<List<T>> findAll(Boolean onlyActive);

  public abstract ResponseEntity<T> add(T toBeSaved);

  public abstract ResponseEntity<T> delete(Long id);

  public abstract ResponseEntity<T> update(T toBeSaved);

  public void logStartOfRequest(ApiOperationType operation, EntityTypeEnum entityType, Object toBeSaved) {
    log.info("Processing request to {} {} [{}]", operation.getAction(), entityType.getType(), toBeSaved);
  }

  public void logStartOfRequest(ApiOperationType operation, EntityTypeEnum entityType) {
    log.info("Processing request to {} {}", operation.getAction(), entityType.getType());
  }

  public void logEndOfRequest(ApiOperationType operation, EntityTypeEnum entityType, Object toBeSaved) {
    log.info("Processed request to {} {} [{}]", operation.getAction(), entityType.getType(), toBeSaved);
  }

  public void logEndOfRequest(ApiOperationType operation, EntityTypeEnum entityType) {
    log.info("Processed request to {} {}", operation.getAction(), entityType.getType());
  }

  public void logStartOfAddRequest(EntityTypeEnum entityType, Object toBeSaved) {
    logStartOfRequest(ApiOperationType.ADD, entityType, toBeSaved);
  }

  public void logEndOfAddRequest(EntityTypeEnum entityType, Object toBeSaved) {
    logEndOfRequest(ApiOperationType.ADD, entityType, toBeSaved);
  }

  public void logStartOfDeleteRequest(EntityTypeEnum entityType, Object toBeSaved) {
    logStartOfRequest(ApiOperationType.DELETE, entityType, toBeSaved);
  }

  public void logEndOfDeleteRequest(EntityTypeEnum entityType, Object toBeSaved) {
    logEndOfRequest(ApiOperationType.DELETE, entityType, toBeSaved);
  }

  public void logStartOfFindAllRequest(EntityTypeEnum entityType) {
    logStartOfRequest(ApiOperationType.FIND_ALL, entityType);
  }

  public void logEndOfFindAllRequest(EntityTypeEnum entityType) {
    logEndOfRequest(ApiOperationType.FIND_ALL, entityType);
  }

  public void logStartOfFindAllActiveRequest(EntityTypeEnum entityType) {
    logStartOfRequest(ApiOperationType.FIND_ALL_ACTIVE, entityType);
  }

  public void logEndOfFindAllActiveRequest(EntityTypeEnum entityType) {
    logEndOfRequest(ApiOperationType.FIND_ALL_ACTIVE, entityType);
  }

  public void logStartOfUpdateRequest(EntityTypeEnum entityType, Object toBeSaved) {
    logStartOfRequest(ApiOperationType.UPDATE, entityType, toBeSaved);
  }

  public void logEndOfUpdateRequest(EntityTypeEnum entityType, Object toBeSaved) {
    logEndOfRequest(ApiOperationType.UPDATE, entityType, toBeSaved);
  }

}
