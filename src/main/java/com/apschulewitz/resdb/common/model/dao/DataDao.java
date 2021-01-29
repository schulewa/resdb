package com.apschulewitz.resdb.common.model.dao;

import com.apschulewitz.resdb.common.model.VersionableDataDto;
import com.apschulewitz.resdb.common.model.VersionableDataEntity;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @param <E> is the type of data entity
 * @param <K> is teh type of key for the data entity
 */
public interface DataDao<E extends VersionableDataEntity<K>, K> {

  List<E> findByStatusIn(List<VersionStatus> statuses);

}
