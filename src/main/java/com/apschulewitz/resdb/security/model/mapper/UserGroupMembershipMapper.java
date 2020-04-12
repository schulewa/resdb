package com.apschulewitz.resdb.security.model.mapper;

import com.apschulewitz.resdb.security.model.dto.PermissionDto;
import com.apschulewitz.resdb.security.model.dto.UserGroupDto;
import com.apschulewitz.resdb.security.model.dto.UserGroupMembershipDto;
import com.apschulewitz.resdb.security.model.dto.UserGroupPermissionDto;
import com.apschulewitz.resdb.security.model.entity.UserGroupMembership;
import com.apschulewitz.resdb.security.model.entity.UserGroupPermission;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class UserGroupMembershipMapper implements EntityMapper<UserGroupMembership, UserGroupMembershipDto> {

    @Override
    public UserGroupMembershipDto toDto(UserGroupMembership userGroupMembership) {
        return toDto(userGroupMembership, false);
    }

    @Override
    public UserGroupMembershipDto toDto(UserGroupMembership userGroupMembership, boolean onlyActive) {
        Collection<UserGroupPermissionDto> userGroupPermissions = null; //new ArrayList<>();
        if (userGroupMembership != null && userGroupMembership.getGroup() != null && userGroupMembership.getGroup().getGroupPermissions() != null) {
            UserGroupPermissionDto.UserGroupPermissionDtoBuilder userGroupBuilder = UserGroupPermissionDto.builder();
            PermissionMapper permissionMapper = new PermissionMapper();
            PermissionDto.PermissionDtoBuilder permissionBuilder = PermissionDto.builder(); // TODO
            userGroupPermissions = userGroupMembership.getGroup().getGroupPermissions().stream()
                    .map(p ->
                        userGroupBuilder.id(p.getId()).permission(permissionMapper.toDto(p.getPermission(), false)).build()
                    ).collect(Collectors.toList());
        }
//        UserGroupPermissionDto.UserGroupPermissionDtoBuilder userGroupPermissionDtoBuilder = userGroupMembership

        UserGroupDto userGroup = UserGroupDto.builder()
                .displayName(userGroupMembership.getGroup().getDisplayName())
                .groupPermissions(userGroupPermissions)
                .id(userGroupMembership.getGroup().getId())
                .name(userGroupMembership.getGroup().getName())
                .status(userGroupMembership.getGroup().getStatus())
                .build();

        return UserGroupMembershipDto.builder()
                .group(userGroup)
                .id(userGroupMembership.getId())
                .validFrom(userGroupMembership.getValidFrom())
                .validTo(userGroupMembership.getValidTo())
                .build();
    }
}
