package com.apschulewitz.resdb.refdata.model.dao;


import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.entity.PublicationType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface PublicationTypeDao extends CrudRepository<PublicationType, Long> {

    PublicationType findByName(String name);

  List<PublicationType> findByStatusIn(List<VersionStatus> livestatuses);

}
