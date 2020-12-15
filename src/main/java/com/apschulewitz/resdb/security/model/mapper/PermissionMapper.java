package com.apschulewitz.resdb.security.model.mapper;

import com.apschulewitz.resdb.common.model.mapper.EntityMapper;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.security.model.dto.PermissionDto;
import com.apschulewitz.resdb.security.model.entity.Permission;
import com.apschulewitz.resdb.security.model.entity.UserGroupMembership;
import com.apschulewitz.resdb.security.model.filter.UserPredicates;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class PermissionMapper { //implements EntityMapper<Permission, PermissionDto> {

//    @Override
    public PermissionDto toDto(Permission permission, boolean onlyActive) {
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

//    @Override
    public PermissionDto toDto(Permission permission) {
        return toDto(permission, false);
    }

  public Collection<PermissionDto> toDto(Collection<UserGroupMembership> memberships) {
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
