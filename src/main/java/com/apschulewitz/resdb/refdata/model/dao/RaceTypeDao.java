package com.apschulewitz.resdb.refdata.model.dao;

import com.apschulewitz.resdb.common.model.dao.DataDao;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.entity.RaceType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by adrianschulewitz on 01/10/2016.
 */
@Transactional
@Repository
public interface RaceTypeDao extends DataDao<RaceType, Long>, CrudRepository<RaceType, Long> {

  List<RaceType> findByStatusIn(List<VersionStatus> livestatuses);

}
