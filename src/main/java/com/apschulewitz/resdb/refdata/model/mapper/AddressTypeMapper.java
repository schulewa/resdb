package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.refdata.model.dto.AddressTypeDto;
import com.apschulewitz.resdb.refdata.model.dto.TaleTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.AddressType;
import com.apschulewitz.resdb.refdata.model.entity.TaleType;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class AddressTypeMapper implements VersionableEntityDtoMapper<AddressType, AddressTypeDto> {


  @Override
  public AddressType toEntity(AddressTypeDto dto) {
    if (dto == null) {
      throw new IllegalArgumentException("Null address type cannot be mapped to entity");
    }

    return AddressType.builder()
      .createdBy(dto.getCreatedBy())
      .id(dto.getId())
      .lastUpdated(dto.getLastUpdated())
      .name(dto.getName())
      .status(VersionStatus.getInstance(dto.getStatus()))
      .updatedBy(dto.getUpdatedBy())
      .versionNumber(dto.getVersionNumber())
      .build();
  }

  @Override
  public AddressTypeDto toDto(AddressType entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Null address type cannot be mapped to dto");
    }

    String status = entity.getStatus() == null ? null : entity.getStatus().name();
    return AddressTypeDto.builder()
      .createdBy(entity.getCreatedBy())
      .id(entity.getId())
      .lastUpdated(entity.getLastUpdated())
      .name(entity.getName())
      .status(status)
      .updatedBy(entity.getUpdatedBy())
      .versionNumber(entity.getVersionNumber())
      .build();
  }

  @Override
  public AddressTypeDto toDto(AddressType entity, boolean onlyActive) {
    if (entity == null) {
      throw new IllegalArgumentException("Null address type cannot be mapped to dto");
    }
    if (VersionStatus.getLiveStatuses().contains(entity.getStatus()) || !onlyActive) {
      return toDto(entity);
    }
    return null;
  }

  @Override
  public AddressType toEntity(AddressTypeDto dto, boolean onlyActive) {
    if (dto == null) {
      throw new IllegalArgumentException("Null address type cannot be mapped to entity");
    }

    VersionStatus status = VersionStatus.getInstance(dto.getStatus());
    if (VersionStatus.getLiveStatuses().contains(status) || !onlyActive) {
      return toEntity(dto);
    }

    return null;
  }

}
