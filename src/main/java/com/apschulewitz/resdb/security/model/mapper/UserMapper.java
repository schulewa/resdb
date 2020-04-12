package com.apschulewitz.resdb.security.model.mapper;

import com.apschulewitz.resdb.security.model.dto.*;
import com.apschulewitz.resdb.security.model.entity.UserAccount;
import com.apschulewitz.resdb.security.model.entity.UserGroupMembership;
import com.apschulewitz.resdb.security.model.entity.UserPassword;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UserMapper /*implements EntityMapper<User, UserDto>*/ {

//    public static UserDto toDto(UserAccount userAccount) {
//        return toDto(userAccount, false);
//    }

    public static UserDto toDto(UserLogonDto dto) {
        return toDto(dto);
    }

    public UserAccountDto toDto(UserAccount userAccount, boolean onlyActive) {
//        UserDto.UserDtoBuilder builder = UserDto.builder();
//
//        Collection<PermissionDto> permissions;
//
//        LocalDateTime now = LocalDateTime.now();
//
//        Predicate<UserGroupMembership> isActiveGroupMembership = (UserGroupMembership m) -> m != null &&
//                (onlyActive && now.isAfter(m.getValidFrom()) && now.isEqual(m.getValidFrom()) && now.isBefore(m.getValidTo())) ||
//                !onlyActive;
//
//        Predicate<UserGroupMembership> hasPermissions = (UserGroupMembership m) -> m != null &&
//                m.getGroup() != null && m.getGroup().getGroupPermissions() != null &&
//                !m.getGroup().getGroupPermissions().isEmpty();
//
//        PermissionMapper permissionMapper = new PermissionMapper();

//        Collection<UserGroupMembershipDto> userGroupMembershipDtos = userAccount.getGroupMemberships().stream()
//                .filter(isActiveGroupMembership)
//                .filter(hasPermissions)
//                .map(userGroupMembership -> UserGroupMembershipDto.builder()
//                        .group(UserGroupDto.builder()
//                        .displayName(userGroupMembership.getGroup().getDisplayName())
//                        .build()))
//                .collect(Collectors.toList());

//        Collection<Permission> perms = userAccount.getGroupMemberships().stream()
//            .filter(UserPredicates.isActiveGroup())
//            .filter(UserPredicates.hasGroupPermissions())
//            .flatMap(membership -> membership.getGroup().getGroupPermissions().stream()
//                .filter(p -> Permission.PermissionStatus.Active.equals(p.getPermission().getStatus()))
//                    .map(UserGroupPermission::getPermission))
//                        .collect(Collectors.toList());
//
//        permissions = perms.stream().map(p -> PermissionMapper.toDto(true, p))
//                .collect(Collectors.toList());

//        return builder
//                .familyName(userAccount.getFamilyName())
//                .firstName(userAccount.getFirstName())
//                .id(userAccount.getId())
//                .logonName(userAccount.getLogonName())
//                .permissions(permissionMapper.toDto(userAccount.getGroupMemberships()))
//                .status(userAccount.getStatus())
//                .build();
        return null;
    }

    private boolean isActiveMembership(UserGroupMembership membership, LocalDateTime now) {
        return membership != null &&
                membership.getValidFrom() != null &&
                now.isAfter(membership.getValidFrom()) && now.isEqual(membership.getValidFrom()) &&
                now.isBefore(membership.getValidTo());
    }

    public static UserAccount toEntity(UserDto userDto) {
        return null;
    }

    public static UserAccount toAccount(UserLogonDto dto) {
        UserAccount userAccount = new UserAccount();
        userAccount.setLogonName(dto.getUserName());
        UserPassword userPassword = new UserPassword();
        userPassword.setUser(userAccount);
        userPassword.setPassword(dto.getPassword());
        userAccount.setPasswords(Arrays.asList(userPassword));
        return userAccount;
    }

//    public static UserDto toDto(JwtUser jwtUser) {
//        UserDto.UserDtoBuilder builder = UserDto.builder();
//        LocalDateTime now = LocalDateTime.now();
//        builder.logonName(jwtUser.getUsername())
//                .token(jwtUser.getToken())
//                .permissions(jwtUser.getAuthorities());
//        return builder.build();
//    }

}
