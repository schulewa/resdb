package com.apschulewitz.resdb.security.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.security.model.dto.UserRegistrationDto;
import com.apschulewitz.resdb.security.model.entity.RegistrationStatus;
import com.apschulewitz.resdb.security.model.entity.UserRegistration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class UserRegistrationMapper implements VersionableEntityDtoMapper<UserRegistration, UserRegistrationDto> {

    @Override
    public UserRegistrationDto toDto(UserRegistration entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Null user registration cannot be mapped to dto");
        }

        String status = entity.getStatus() == null ?  null : entity.getStatus().name();
        return UserRegistrationDto.builder()
                .createdBy(entity.getCreatedBy())
                .familyName(entity.getFamilyName())
                .firstName(entity.getFirstName())
                .id(entity.getId())
                .email(entity.getEmail())
                .emailVerified(entity.getEmailVerified())
                .lastUpdated(entity.getLastUpdated())
                .registrationStatus(entity.getRegistrationStatus().name())
                .status(status)
                .updatedBy(entity.getUpdatedBy())
                .verificationCode(entity.getVerificationCode())
                .versionNumber(entity.getVersionNumber())
                .build();
    }

    @Override
    public UserRegistrationDto toDto(UserRegistration entity, boolean onlyActive) {
      if (RegistrationStatus.requiresFurtherProcessing(entity.getRegistrationStatus()) || !onlyActive) {
        return toDto(entity);
      }
      return null;
    }

  @Override
  public UserRegistration toEntity(UserRegistrationDto dto, boolean onlyActive) {
      if (dto == null) {
        throw new IllegalArgumentException("Null dto supplied");
      }

      VersionStatus status = null;
      if (!StringUtils.isEmpty(dto.getStatus())) {
        status = VersionStatus.getInstance(dto.getStatus());
      }
      RegistrationStatus registrationStatus = null;
      if (!StringUtils.isEmpty(dto.getRegistrationStatus())) {
        registrationStatus = RegistrationStatus.getStatusFor(dto.getRegistrationStatus());
      }
      return UserRegistration.builder()
        .createdBy(dto.getCreatedBy())
        .emailVerified(dto.getEmailVerified())
        .email(dto.getEmail())
        .familyName(dto.getFamilyName())
        .firstName(dto.getFirstName())
        .id(dto.getId())
        .lastUpdated(dto.getLastUpdated())
        .registrationStatus(registrationStatus)
        .status(status)
        .updatedBy(dto.getUpdatedBy())
        .verificationCode(dto.getVerificationCode())
        .build();
  }

}
