package com.apschulewitz.resdb.refdata.model.dao;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.entity.Artefact;
import com.apschulewitz.resdb.refdata.model.entity.ArtefactType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by adrianschulewitz on 01/10/2016.
 */
@Transactional
@Repository
public interface ArtefactTypeDao extends CrudRepository<ArtefactType, Long> {

  List<ArtefactType> findByStatusIn(List<VersionStatus> livestatuses);

  List<ArtefactType> findByStatusInAndNameStartsWith(List<VersionStatus> livestatuses, String name);
}
