package com.apschulewitz.resdb.common.model.dao;

import com.apschulewitz.resdb.common.model.entity.SequenceNumber;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by adrianschulewitz on 01/10/2016.
 */
public interface SequenceNumberDao extends CrudRepository<SequenceNumber, Long> {
}
