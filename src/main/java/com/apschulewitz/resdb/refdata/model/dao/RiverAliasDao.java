package com.apschulewitz.resdb.refdata.model.dao;

import com.apschulewitz.resdb.refdata.model.entity.RiverAlias;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by adrianschulewitz on 01/10/2016.
 */
@Transactional
@Repository
public interface RiverAliasDao extends CrudRepository<RiverAlias, Long> {
}
