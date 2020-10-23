package com.apschulewitz.resdb.security.model.mapper;

import com.apschulewitz.resdb.refdata.model.entity.AccountStatus;
import com.apschulewitz.resdb.security.model.dto.UserGroupPermissionDto;
import com.apschulewitz.resdb.security.model.entity.Permission;
import com.apschulewitz.resdb.security.model.entity.UserGroup;
import com.apschulewitz.resdb.security.model.entity.UserGroupPermission;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class UserGroupPermissionMapperTest {

  private PermissionMapper permissionMapper = new PermissionMapper();
  private UserGroupPermissionMapper userGroupPermissionMapper = new UserGroupPermissionMapper(permissionMapper);

  @Test
  public void given_usergrouppermission_when_toDto_is_executed_then_return_dto() {
    // given
    UserGroupPermission userGroupPermission = constructUserGroupPermission();

    // when
    UserGroupPermissionDto userGroupPermissionDto = userGroupPermissionMapper.toDto(userGroupPermission);

    // then
    assertNotNull(userGroupPermissionDto);
  }

  @Test
  public void given_null_usergrouppermission_when_toDto_is_executed_then_return_dto() {
    // given

    // when
    UserGroupPermissionDto userGroupPermissionDto = userGroupPermissionMapper.toDto(null);

    // then
    assertNotNull(userGroupPermissionDto);
  }

  @Test
  public void given_usergrouppermission_and_null_permission_when_toDto_is_executed_then_return_dto() {
    // given
    UserGroupPermission userGroupPermission = constructUserGroupPermission();
    userGroupPermission.setPermission(null);

    // when
    UserGroupPermissionDto userGroupPermissionDto = userGroupPermissionMapper.toDto(userGroupPermission);

    // then
    assertNotNull(userGroupPermissionDto);
  }

  private UserGroupPermission constructUserGroupPermission() {
    Permission permission1 = Permission.builder()
      .name("Permission1")
      .status(Permission.PermissionStatus.Active)
      .id(1L)
      .operationType(Permission.OperationType.Create)
      .description("Permission description 1")
      .build();

    UserGroup userGroup = UserGroup.builder()
      .displayName("User group display name")
      .id(3L)
      .name("User group name")
      .status(AccountStatus.Active)
      .build();

    UserGroupPermission userGroupPermission1 = UserGroupPermission.builder()
      .permission(permission1)
      .id(12L)
      .group(userGroup)
      .build();

    userGroup.setGroupPermissions(Collections.singletonList(userGroupPermission1));

    return userGroupPermission1;
  }
}
