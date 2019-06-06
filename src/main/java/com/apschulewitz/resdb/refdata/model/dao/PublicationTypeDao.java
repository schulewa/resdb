package com.apschulewitz.resdb.refdata.model.dao;


import com.apschulewitz.resdb.refdata.model.entity.PublicationType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface PublicationTypeDao extends CrudRepository<PublicationType, Long> {

    PublicationType findByName(String name);

}