package com.apschulewitz.resdb.security.model.dto;

import com.apschulewitz.resdb.refdata.model.entity.AccountStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.Collection;

@Builder
@Getter
public class UserGroupDto {

    private Long id;
    private String name;
    private String displayName;
    private AccountStatus status;
    private Collection<UserGroupPermissionDto> groupPermissions;

}
