package com.apschulewitz.resdb.refdata.model.dao;


import com.apschulewitz.resdb.refdata.model.entity.PublicationRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface PublicationRoleDao extends CrudRepository<PublicationRole, Long> {

}
