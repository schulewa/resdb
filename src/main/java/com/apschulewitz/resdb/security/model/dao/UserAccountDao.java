package com.apschulewitz.resdb.security.model.dao;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.security.model.dto.UserAccountProjection;
import com.apschulewitz.resdb.security.model.entity.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by adrianschulewitz on 01/10/2016.
 */
@Repository
public interface UserAccountDao extends CrudRepository<UserAccount, Long> {

    UserAccount findByLogonName(String logonName);

    List<UserAccount> findByStatusIn(List<VersionStatus> livestatuses);

}
