package com.apschulewitz.resdb.security.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.security.model.dto.AuthenticationConfigurationDto;
import com.apschulewitz.resdb.security.model.entity.AuthenticationConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class AuthenticationConfigurationMapper
  implements VersionableEntityDtoMapper<AuthenticationConfiguration, AuthenticationConfigurationDto> {

  public AuthenticationConfigurationDto toDto(AuthenticationConfiguration entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Null authentication configuration cannot be mapped to dto");
    }

    String status = null;
    if (entity.getStatus() != null) {
      status = entity.getStatus().name();
    }

    return AuthenticationConfigurationDto.builder()
      .createdBy(entity.getCreatedBy())
      .id(entity.getId())
      .lastUpdated(entity.getLastUpdated())
      .maximumPasswordAgeInDays(entity.getMaximumPasswordAgeInDays())
      .maximumPasswordLength(entity.getMaximumPasswordLength())
      .minimumLowercaseCharacters(entity.getMinimumLowercaseCharacters())
      .minimumNumberCharacters(entity.getMinimumNumberCharacters())
      .minimumPasswordLength(entity.getMinimumPasswordLength())
      .minimumSpecialCharacters(entity.getMinimumSpecialCharacters())
      .minimumUppercaseCharacters(entity.getMinimumUppercaseCharacters())
      .status(status)
      .updatedBy(entity.getUpdatedBy())
      .versionNumber(entity.getVersionNumber())
      .build();
  }

  public AuthenticationConfiguration toEntity(AuthenticationConfigurationDto dto) {
    if (dto == null) {
      throw new IllegalArgumentException("Null authentication configuration cannot be mapped to entity");
    }

    VersionStatus status = null;
    if (!StringUtils.isEmpty(dto.getStatus())) {
      status = VersionStatus.getInstance(dto.getStatus());
    }

    return AuthenticationConfiguration.builder()
      .createdBy(dto.getCreatedBy())
      .id(dto.getId())
      .lastUpdated(dto.getLastUpdated())
      .maximumPasswordAgeInDays(dto.getMaximumPasswordAgeInDays())
      .maximumPasswordLength(dto.getMaximumPasswordLength())
      .minimumLowercaseCharacters(dto.getMinimumLowercaseCharacters())
      .minimumNumberCharacters(dto.getMinimumNumberCharacters())
      .minimumPasswordLength(dto.getMinimumPasswordLength())
      .minimumSpecialCharacters(dto.getMinimumSpecialCharacters())
      .minimumUppercaseCharacters(dto.getMinimumUppercaseCharacters())
      .status(status)
      .updatedBy(dto.getUpdatedBy())
      .versionNumber(dto.getVersionNumber())
      .build();
  }

  @Override
  public AuthenticationConfigurationDto toDto(AuthenticationConfiguration entity, boolean onlyActive) {
    if (entity == null) {
      throw new IllegalArgumentException("Null authentication configuration cannot be mapped to dto");
    }

    if (onlyActive && !VersionStatus.isActive(entity.getStatus())) {
      return null;
    }

    return toDto(entity);
  }

  @Override
  public AuthenticationConfiguration toEntity(AuthenticationConfigurationDto dto, boolean onlyActive) {
    if (dto == null) {
      throw new IllegalArgumentException("Null authentication configuration cannot be mapped to entity");
    }

    VersionStatus status = VersionStatus.getInstance(dto.getStatus());
    if (onlyActive && !VersionStatus.isActive(status)) {
      return null;
    }

    return toEntity(dto);
  }


}
