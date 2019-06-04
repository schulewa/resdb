package com.apschulewitz.resdb.security.model.mapper;

import com.apschulewitz.resdb.security.model.dto.PermissionDto;
import com.apschulewitz.resdb.security.model.entity.Permission;
import com.apschulewitz.resdb.security.model.entity.UserGroupMembership;
import com.apschulewitz.resdb.security.model.filter.UserPredicates;

import java.util.Collection;
import java.util.stream.Collectors;

public class PermissionMapper {

    public static PermissionDto toDto(boolean onlyActive, Permission permission) {
        PermissionDto.PermissionDtoBuilder builder = PermissionDto.builder();

        if (onlyActive && !Permission.PermissionStatus.Active.equals(permission.getStatus())) {
            return null;
        }

        return builder.description(permission.getDescription())
                .id(permission.getId())
                .name(permission.getName())
                .operationType(permission.getOperationType())
                .build();
    }

    public static Collection<PermissionDto> toDto(Collection<UserGroupMembership> memberships) {
        PermissionDto.PermissionDtoBuilder builder = PermissionDto.builder();

        return memberships.stream()
                .filter(UserPredicates.hasGroupPermissions())
                .filter(UserPredicates.isActiveGroup())
                .flatMap(membership -> membership.getGroup().getGroupPermissions().stream()
//                        .filter(p -> Permission.PermissionStatus.Active.equals(p.getPermission().getStatus()))
                        .filter(UserPredicates.isActiveGroupPermission())
                        .map(p -> builder.name(p.getPermission().getName())
                                        .operationType(p.getPermission().getOperationType())
                                        .description(p.getPermission().getDescription())
                                        .id(p.getId())
                                        .build()))
                .collect(Collectors.toList());
    }
}
