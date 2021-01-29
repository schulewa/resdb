package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.common.utils.ResearchDatabaseCommonException;
import com.apschulewitz.resdb.refdata.model.dto.CountryDto;
import com.apschulewitz.resdb.refdata.model.dto.ImageDto;
import com.apschulewitz.resdb.refdata.model.entity.Country;
import com.apschulewitz.resdb.refdata.model.entity.Image;
import com.apschulewitz.resdb.refdata.model.entity.ImageType;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;

@Component
//@NoArgsConstructor
public class CountryMapper implements VersionableEntityDtoMapper<Country, CountryDto> {

  private ImageMapper imageMapper;

  public CountryMapper(ImageMapper imageMapper) {
    this.imageMapper = imageMapper;
  }

  @Override
  public Country toEntity(CountryDto dto) {

    if (dto == null) {
      throw new IllegalArgumentException("Null country cannot be mapped to entity");
    }

    File imageFile;
    Image image = null;

    if (dto.getFlagImage() != null) {
      image = imageMapper.toEntity(dto.getFlagImage());
    }

    if (dto.getFlagImage() != null) {

      ImageType imageType = null;
      String flagFilePath = null;
      String flagFileName = null;

      if (!StringUtils.isEmpty(dto.getFlagImage().getFilePath()) &&
        !StringUtils.isEmpty(dto.getFlagImage().getFileName())) {

        flagFilePath = dto.getFlagImage().getFilePath();
        flagFileName = dto.getFlagImage().getFileName();

        if (dto.getFlagImage().getImageType() != null) {
          imageType = ImageType.builder()
            .createdBy(dto.getFlagImage().getImageType().getCreatedBy())
            .id(dto.getFlagImage().getImageType().getId())
            .lastUpdated(dto.getFlagImage().getImageType().getLastUpdated())
            .name(dto.getFlagImage().getImageType().getName())
            .status(VersionStatus.getInstance(dto.getFlagImage().getImageType().getStatus()))
            .updatedBy(dto.getFlagImage().getImageType().getUpdatedBy())
            .versionNumber(dto.getFlagImage().getImageType().getVersionNumber())
            .build();
        }

      }

      image = Image.builder()
        .createdBy(dto.getFlagImage().getCreatedBy())
        .filePath(flagFilePath)
        .fileName(flagFileName)
        .id(dto.getFlagImage().getId())
        .imageType(imageType)
        .lastUpdated(dto.getFlagImage().getLastUpdated())
        .status(VersionStatus.getInstance(dto.getFlagImage().getStatus()))
        .updatedBy(dto.getFlagImage().getUpdatedBy())
        .versionNumber(dto.getFlagImage().getVersionNumber())
        .build();
    }

    return Country.builder()
      .code(dto.getCode())
      .createdBy(dto.getCreatedBy())
      .flagImage(image)
      .id(dto.getId())
      .lastUpdated(dto.getLastUpdated())
      .name(dto.getName())
      .sovereignty(dto.getSovereignty())
      .stateName(dto.getStateName())
      .status(VersionStatus.getInstance(dto.getStatus()))
      .updatedBy(dto.getUpdatedBy())
      .versionNumber(dto.getVersionNumber())
      .build();
  }

  @Override
  public CountryDto toDto(Country entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Null country cannot be mapped to dto");
    }

    ImageDto imageDto = null;
    if (entity.getFlagImage() != null) {
      imageDto = imageMapper.toDto(entity.getFlagImage());
    }

    String status = entity.getStatus() == null ? null : entity.getStatus().name();
    return CountryDto.builder()
      .code(entity.getCode())
      .createdBy(entity.getCreatedBy())
      .flagImage(imageDto)
      .id(entity.getId())
      .lastUpdated(entity.getLastUpdated())
      .name(entity.getName())
      .sovereignty(entity.getSovereignty())
      .stateName(entity.getStateName())
      .status(status)
      .updatedBy(entity.getUpdatedBy())
      .versionNumber(entity.getVersionNumber())
      .build();
  }

  @Override
  public CountryDto toDto(Country entity, boolean onlyActive) {
    if (entity == null) {
      throw new IllegalArgumentException("Null country cannot be mapped to dto");
    }

    if (VersionStatus.getLiveStatuses().contains(entity.getStatus()) || !onlyActive) {
      return toDto(entity);
    }

    return null;
  }

  @Override
  public Country toEntity(CountryDto dto, boolean onlyActive) {
    if (dto == null) {
      throw new IllegalArgumentException("Null country cannot be mapped to entity");
    }

    VersionStatus status = null;
    if (!StringUtils.isEmpty(dto.getStatus())) {
      status = VersionStatus.getInstance(dto.getStatus());
    }

    if (VersionStatus.getLiveStatuses().contains(status) || !onlyActive) {
      return toEntity(dto);
    }

    return null;
  }

}
