package com.apschulewitz.resdb.refdata.model.dao;

import com.apschulewitz.resdb.refdata.model.entity.OtherAddress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by adrianschulewitz on 01/10/2016.
 */
@Transactional
@Repository
public interface OtherAddressDao extends CrudRepository<OtherAddress, Long> {
}
