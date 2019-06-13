package com.apschulewitz.resdb.refdata.model.dao;


import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.entity.CalendarType;
import com.apschulewitz.resdb.refdata.model.entity.MeasureType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by adrianschulewitz on 01/10/2016.
 */
@Transactional
@Repository
public interface MeasureTypeDao extends CrudRepository<MeasureType, Long> {

    MeasureType findByNameEquals(String name);

    List<MeasureType> findByStatusIn(List<VersionStatus> livestatuses);

}
