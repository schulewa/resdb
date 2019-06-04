package com.apschulewitz.resdb.security.model.dao;

import com.apschulewitz.resdb.security.model.entity.UserGroupMembership;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by adrianschulewitz on 01/10/2016.
 */
@Repository
public interface UserGroupMembershipDao extends CrudRepository<UserGroupMembership, Long> {

    List<UserGroupMembership> findByUser_logonName(String name);

    List<UserGroupMembership> findByGroup_Id(Long groupId);

    List<UserGroupMembership> findByGroup_Name(String name);

}
