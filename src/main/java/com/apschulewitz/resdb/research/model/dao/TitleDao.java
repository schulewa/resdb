package com.apschulewitz.resdb.research.model.dao;

import com.apschulewitz.resdb.common.model.entity.Title;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by adrianschulewitz on 01/10/2016.
 */
@Transactional
@Repository
public interface TitleDao extends CrudRepository<Title, Long> {

  List<Title> findByStatusIn(List<VersionStatus> livestatuses);

  List<Title> findByStatusInAndTitleStartsWith(List<VersionStatus> livestatuses, String title);

}
