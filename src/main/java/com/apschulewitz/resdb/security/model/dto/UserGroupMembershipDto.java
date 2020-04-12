package com.apschulewitz.resdb.security.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@Getter
@ToString
public class UserGroupMembershipDto {

    private Long id;
    private UserGroupDto group;
    private LocalDateTime validFrom;
    private LocalDateTime validTo;

}
