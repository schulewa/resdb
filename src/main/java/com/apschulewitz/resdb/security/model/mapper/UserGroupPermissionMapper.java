package com.apschulewitz.resdb.security.model.mapper;

import com.apschulewitz.resdb.security.model.dto.PermissionDto;
import com.apschulewitz.resdb.security.model.dto.UserGroupPermissionDto;
import com.apschulewitz.resdb.security.model.entity.UserGroupPermission;
import org.springframework.stereotype.Component;

@Component
public class UserGroupPermissionMapper implements EntityMapper<UserGroupPermission, UserGroupPermissionDto> {

    private final PermissionMapper permissionMapper;

    public UserGroupPermissionMapper(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    @Override
    public UserGroupPermissionDto toDto(UserGroupPermission userGroupPermission) {
        PermissionDto permission = null;
        if (userGroupPermission != null && userGroupPermission.getPermission() != null) {
            permission = permissionMapper.toDto(userGroupPermission.getPermission());
        }

        // do not map the group else risk circular mapping
      Long userGroupPermissionId = userGroupPermission != null ? userGroupPermission.getId() : null;
        return UserGroupPermissionDto.builder()
                .id(userGroupPermissionId)
                .permission(permission)
                .build();
    }
}
