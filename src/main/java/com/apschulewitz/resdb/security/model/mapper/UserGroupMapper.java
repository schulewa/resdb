package com.apschulewitz.resdb.security.model.mapper;

import com.apschulewitz.resdb.security.model.dto.UserGroupDto;
import com.apschulewitz.resdb.security.model.dto.UserGroupPermissionDto;
import com.apschulewitz.resdb.security.model.entity.UserGroup;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class UserGroupMapper implements EntityMapper<UserGroup, UserGroupDto> {

    private final UserGroupPermissionMapper userGroupPermissionMapper;

    public UserGroupMapper(UserGroupPermissionMapper userGroupPermissionMapper) {
        this.userGroupPermissionMapper = userGroupPermissionMapper;
    }

    @Override
    public UserGroupDto toDto(UserGroup userGroup) {
        Collection<UserGroupPermissionDto> userGroupPermissions = null;

        if (userGroup.getGroupPermissions() != null) {
            userGroupPermissions = userGroup.getGroupPermissions().stream()
                    .map(userGroupPermission -> userGroupPermissionMapper.toDto(userGroupPermission))
                    .collect(Collectors.toList());
        }

        return UserGroupDto.builder()
                .displayName(userGroup.getDisplayName())
                .groupPermissions(userGroupPermissions)
                .id(userGroup.getId())
                .name(userGroup.getName())
                .status(userGroup.getStatus())
                .build();

    }

    @Override
    public UserGroupDto toDto(UserGroup userGroup, boolean onlyActive) {
        return null;
    }
}
