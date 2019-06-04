package com.apschulewitz.resdb.common.model.dao;

import com.apschulewitz.resdb.common.model.entity.ApplicationVersion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by adrianschulewitz on 01/10/2016.
 */
@Repository
public interface ApplicationVersionDao extends CrudRepository<ApplicationVersion, Long> {
}
