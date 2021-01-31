package com.apschulewitz.resdb.refdata.model.dao;

import com.apschulewitz.resdb.common.model.dao.DataDao;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.entity.AddressType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by adrianschulewitz on 01/10/2016.
 */
public interface AddressTypeDao extends DataDao<AddressType, Long>, CrudRepository<AddressType, Long> {

  List<AddressType> findByStatusIn(List<VersionStatus> livestatuses);

  List<AddressType> findByStatusInAndNameStartsWith(List<VersionStatus> livestatuses, String name);

}
