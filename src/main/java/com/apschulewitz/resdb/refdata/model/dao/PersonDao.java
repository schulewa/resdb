package com.apschulewitz.resdb.refdata.model.dao;

import com.apschulewitz.resdb.common.model.dao.DataDao;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by adrianschulewitz on 01/10/2016.
 */
@Transactional
@Repository
public interface PersonDao extends DataDao<Person, Long>, CrudRepository<Person, Long> {

  List<Person> findByStatusIn(List<VersionStatus> livestatuses);

  Person findByFamilyNameAndMiddleNameAndFirstName(String familyName, String middleName, String firstName);

}
