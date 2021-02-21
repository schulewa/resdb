package com.apschulewitz.resdb.security.model.mapper;

import com.apschulewitz.resdb.config.UserAuthenticationConfiguration;
import com.apschulewitz.resdb.security.model.dto.UserGroupPermissionDto;
import com.apschulewitz.resdb.security.model.entity.UserGroupPermission;
import com.apschulewitz.resdb.security.model.helper.SecurityTestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
//  UserAuthenticationConfiguration.class,
  PermissionMapper.class,
  SecurityTestHelper.class,
  UserGroupPermissionMapper.class
})
public class UserGroupPermissionMapperTest {

  @Autowired
  private UserGroupPermissionMapper userGroupPermissionMapper;

  @Autowired
  private SecurityTestHelper securityTestHelper;

//  @Before
//  public void beforeEachTest() {
//    ReflectionTestUtils.setField(securityTestHelper, "authentication.strength", 12, Integer.class);
//  }

  @Test
  public void given_usergrouppermission_when_toDto_is_executed_then_return_dto() {
    // given
    UserGroupPermission userGroupPermission = securityTestHelper.constructActiveUserGroupPermission(); //constructUserGroupPermission();

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
    UserGroupPermission userGroupPermission = securityTestHelper.constructActiveUserGroupPermission(); //constructUserGroupPermission();
    userGroupPermission.setPermission(null);

    // when
    UserGroupPermissionDto userGroupPermissionDto = userGroupPermissionMapper.toDto(userGroupPermission);

    // then
    assertNotNull(userGroupPermissionDto);
  }

//  private UserGroupPermission constructUserGroupPermission() {
//    Permission permission1 = Permission.builder()
//      .name("Permission1")
//      .status(Permission.PermissionStatus.Active)
//      .id(1L)
//      .operationType(Permission.OperationType.Create)
//      .description("Permission description 1")
//      .build();
//
//    UserGroup userGroup = UserGroup.builder()
//      .displayName("User group display name")
//      .id(3L)
//      .name("User group name")
//      .status(AccountStatus.Active)
//      .build();
//
//    UserGroupPermission userGroupPermission1 = UserGroupPermission.builder()
//      .permission(permission1)
//      .id(12L)
//      .group(userGroup)
//      .build();
//
//    userGroup.setGroupPermissions(Collections.singletonList(userGroupPermission1));
//
//    return userGroupPermission1;
//  }
}
