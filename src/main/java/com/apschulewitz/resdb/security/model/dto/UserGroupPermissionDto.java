package com.apschulewitz.resdb.security.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class UserGroupPermissionDto {

    private Long id;
    private UserGroupDto group;
    private PermissionDto permission;

}
