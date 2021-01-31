package com.apschulewitz.resdb.refdata.model.dao;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.entity.Artefact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by adrianschulewitz on 01/10/2016.
 */
@Transactional
@Repository
public interface ArtefactDao extends CrudRepository<Artefact, Long> {

  List<Artefact> findByStatusIn(List<VersionStatus> livestatuses);

  List<Artefact> findByStatusInAndNameStartsWith(List<VersionStatus> livestatuses, String name);
}
