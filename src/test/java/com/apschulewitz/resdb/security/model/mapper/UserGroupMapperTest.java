package com.apschulewitz.resdb.security.model.mapper;

import com.apschulewitz.resdb.refdata.model.entity.AccountStatus;
import com.apschulewitz.resdb.security.model.dto.UserGroupDto;
import com.apschulewitz.resdb.security.model.entity.Permission;
import com.apschulewitz.resdb.security.model.entity.UserGroup;
import com.apschulewitz.resdb.security.model.entity.UserGroupPermission;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
public class UserGroupMapperTest {

  private UserGroupMapper userGroupMapper;
  private UserGroupPermissionMapper userGroupPermissionMapper;
  private PermissionMapper permissionMapper;

  @Before
  public void beforeEachTest() {
    permissionMapper = new PermissionMapper();
    userGroupPermissionMapper = new UserGroupPermissionMapper(permissionMapper);
    userGroupMapper = new UserGroupMapper(userGroupPermissionMapper);
  }

  @Test
  public void given_valid_usergroup_when_toDto_is_executed_then_return_dto() {
    // given
    UserGroup userGroup = constructNewUserGroup();

    // when
    UserGroupDto userGroupDto = userGroupMapper.toDto(userGroup);

    // then
    assertNotNull(userGroupDto);
  }

  @Test
  public void given_valid_usergroup_and_null_grouppermissions_when_toDto_is_executed_then_return_dto() {
    // given
    UserGroup userGroup = constructNewUserGroup();
    userGroup.setGroupPermissions(null);

    // when
    UserGroupDto userGroupDto = userGroupMapper.toDto(userGroup);

    // then
    assertNotNull(userGroupDto);
  }

  @Test
  public void given_active_usergroup_and_onlyactive_when_toDto_is_executed_then_return_dto() {
    // given
    UserGroup userGroup = constructNewUserGroup();

    // when
    UserGroupDto userGroupDto = userGroupMapper.toDto(userGroup, true);

    // then
    assertNotNull(userGroupDto);
  }

  @Test
  public void given_inactive_usergroup_and_not_onlyactive_when_toDto_is_executed_then_return_dto() {
    // given
    UserGroup userGroup = constructNewUserGroup();
    userGroup.setStatus(AccountStatus.Inactive);

    // when
    UserGroupDto userGroupDto = userGroupMapper.toDto(userGroup, false);

    // then
    assertNotNull(userGroupDto);
  }

  @Test
  public void given_inactive_usergroup_and_onlyactive_when_toDto_is_executed_then_return_dto() {
    // given
    UserGroup userGroup = constructNewUserGroup();
    userGroup.setStatus(AccountStatus.Inactive);

    // when
    UserGroupDto userGroupDto = userGroupMapper.toDto(userGroup, true);

    // then
    assertNull(userGroupDto);
  }

  private UserGroup constructNewUserGroup() {

    Permission permission1 = Permission.builder()
      .name("Permission1")
      .status(Permission.PermissionStatus.Active)
      .id(1L)
      .operationType(Permission.OperationType.Create)
      .description("Permission description 1")
      .build();

    UserGroupPermission userGroupPermission = UserGroupPermission.builder()
      .permission(permission1)
      .id(12L)
      .build();

    List<UserGroupPermission> userGroupPermissions = Collections.singletonList(userGroupPermission);

    UserGroup userGroup = UserGroup.builder()
      .status(AccountStatus.Active)
      .name("User group")
      .id(1L)
      .displayName("User group display name")
      .groupPermissions(userGroupPermissions)
      .build();

    userGroupPermission.setGroup(userGroup);

    return userGroup;
  }

}
