package com.apschulewitz.resdb.security.model.mapper;

import com.apschulewitz.resdb.security.model.dto.PermissionDto;
import com.apschulewitz.resdb.security.model.entity.Permission;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
public class PermissionMapperTest {

  private PermissionMapper permissionMapper = new PermissionMapper();

  @Test
  public void given_active_permission_when_toDto_is_executed_then_return_dto() {
    // given
    Permission permission = constructNewPermission();

    // when
    PermissionDto permissionDto = permissionMapper.toDto(permission);

    // then
    assertNotNull(permissionDto);
  }

  @Test
  public void given_inactive_permission_and_onlyactive_when_toDto_is_executed_then_return_null() {
    // given
    Permission permission = constructNewPermission();
    permission.setStatus(Permission.PermissionStatus.Suspended);

    // when
    PermissionDto permissionDto = permissionMapper.toDto(permission, true);

    // then
    assertNull(permissionDto);
  }

  @Test
  public void given_inactive_permission_and_not_onlyactive_when_toDto_is_executed_then_return_null() {
    // given
    Permission permission = constructNewPermission();
    permission.setStatus(Permission.PermissionStatus.Suspended);

    // when
    PermissionDto permissionDto = permissionMapper.toDto(permission, false);

    // then
    assertNotNull(permissionDto);
  }

  private Permission constructNewPermission() {
    return Permission.builder()
      .name("Permission name")
      .operationType(Permission.OperationType.Create)
      .status(Permission.PermissionStatus.Active)
      .id(1L)
      .description("Permission description")
      .build();
  }
}
