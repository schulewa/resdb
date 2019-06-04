package com.apschulewitz.resdb.security.model.dao;

import com.apschulewitz.resdb.security.model.entity.UserGroupPermission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by adrianschulewitz on 01/10/2016.
 */
@Repository
public interface UserGroupPermissionDao extends CrudRepository<UserGroupPermission, Long> {
}
