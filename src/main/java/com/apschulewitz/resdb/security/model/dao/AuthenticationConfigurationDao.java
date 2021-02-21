package com.apschulewitz.resdb.security.model.dao;

import com.apschulewitz.resdb.common.model.dao.DataDao;
import com.apschulewitz.resdb.security.model.entity.AuthenticationConfiguration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationConfigurationDao extends DataDao<AuthenticationConfiguration, Long>,
                                                        CrudRepository<AuthenticationConfiguration, Long> {

}
