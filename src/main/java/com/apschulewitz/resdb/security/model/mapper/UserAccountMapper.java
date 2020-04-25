package com.apschulewitz.resdb.security.model.mapper;

import com.apschulewitz.resdb.refdata.model.mapper.LanguageMapper;
import com.apschulewitz.resdb.security.model.dto.LanguageDto;
import com.apschulewitz.resdb.security.model.dto.UserAccountDto;
import com.apschulewitz.resdb.security.model.dto.UserGroupMembershipDto;
import com.apschulewitz.resdb.security.model.entity.UserAccount;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class UserAccountMapper implements EntityMapper<UserAccount, UserAccountDto> {

    private final UserGroupMembershipMapper userGroupMembershipMapper;

    private final LanguageMapper languageMapper;

    public UserAccountMapper(UserGroupMembershipMapper userGroupMembershipMapper,
                             LanguageMapper languageMapper) {
        this.userGroupMembershipMapper = userGroupMembershipMapper;
        this.languageMapper = languageMapper;
    }

    @Override
    public UserAccountDto toDto(UserAccount userAccount) {
        if (userAccount == null) {
            throw new IllegalArgumentException("Null user account cannot be mapped to dto");
        }

        Collection<UserGroupMembershipDto> userGroupMemberships = null;
        if (userAccount.getGroupMemberships() != null) {
            userGroupMemberships = userAccount.getGroupMemberships().stream()
                    .map(ugm -> userGroupMembershipMapper.toDto(ugm, true))
                    .collect(Collectors.toList());
        }

        LanguageDto preferredLanguage = null;
        if (userAccount.getPreferredLanguage() != null) {
            languageMapper.toDto(userAccount.getPreferredLanguage());
        }

        return UserAccountDto.builder()
                .familyName(userAccount.getFamilyName())
                .firstName(userAccount.getFirstName())
                .groupMemberships(userGroupMemberships)
                .id(userAccount.getId())
                .invalidAccessCount(userAccount.getInvalidAccessCount())
                .lastLogon(userAccount.getLastLogon())
                .logonName(userAccount.getLogonName())
                .passwordUpdated(userAccount.getPasswordUpdated())
                .preferredLanguage(preferredLanguage)
                .status(userAccount.getStatus())
                .templateUser(userAccount.isTemplateUser())
                .build();
    }

    @Override
    public UserAccountDto toDto(UserAccount entity, boolean onlyActive) {
        return null;
    }

}
