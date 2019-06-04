package com.apschulewitz.resdb.config.model.dao;

import com.apschulewitz.resdb.config.model.entity.SystemParameter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemParameterDao extends CrudRepository<SystemParameter, Long> {

    SystemParameter findByName(String name);

}
