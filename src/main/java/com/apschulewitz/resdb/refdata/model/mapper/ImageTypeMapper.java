package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.refdata.model.dto.ImageTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.ImageType;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ImageTypeMapper implements VersionableEntityDtoMapper<ImageType, ImageTypeDto> {


  @Override
  public ImageType toEntity(ImageTypeDto dto) {

    if (dto == null) {
      throw new IllegalArgumentException("Null image type cannot be mapped to entity");
    }

    VersionStatus status = dto.getStatus() == null ? null : VersionStatus.getInstance(dto.getStatus());
    return ImageType.builder()
      .createdBy(dto.getCreatedBy())
      .id(dto.getId())
      .lastUpdated(dto.getLastUpdated())
      .name(dto.getName())
      .status(status)
      .updatedBy(dto.getUpdatedBy())
      .versionNumber(dto.getVersionNumber())
      .build();
  }

  @Override
  public ImageTypeDto toDto(ImageType entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Null image type cannot be mapped to dto");
    }

    String status = entity.getStatus() == null ? null : entity.getStatus().name();
    return ImageTypeDto.builder()
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
  public ImageTypeDto toDto(ImageType entity, boolean onlyActive) {
    if (entity == null) {
      throw new IllegalArgumentException("Null image type cannot be mapped to dto");
    }

    if (VersionStatus.getLiveStatuses().contains(entity.getStatus()) || !onlyActive) {
      return toDto(entity);
    }
    return null;
  }

  @Override
  public ImageType toEntity(ImageTypeDto dto, boolean onlyActive) {
    if (dto == null) {
      throw new IllegalArgumentException("Null image type cannot be mapped to entity");
    }

    VersionStatus status = VersionStatus.getInstance(dto.getStatus());
    if (VersionStatus.getLiveStatuses().contains(status) || !onlyActive) {
      return toEntity(dto);
    }

    return null;
  }

}
