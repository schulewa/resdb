package com.apschulewitz.resdb.common.model.dao;

import com.apschulewitz.resdb.common.model.entity.DataClassMethodArgument;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by adrianschulewitz on 01/10/2016.
 */
@Repository
public interface DataClassMethodArgumentDao extends CrudRepository<DataClassMethodArgument, Long> {
}
