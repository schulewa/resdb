package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.entity.YesNoChoice;
import com.apschulewitz.resdb.common.utils.LoggingUtils;
import com.apschulewitz.resdb.common.utils.StringUtils;
import com.apschulewitz.resdb.refdata.model.dto.LanguageGroupDto;
import com.apschulewitz.resdb.refdata.model.entity.Language;
import com.apschulewitz.resdb.refdata.model.dto.LanguageDto;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.refdata.model.entity.LanguageGroup;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Component;

@Component
public class LanguageMapper implements VersionableEntityDtoMapper<Language, LanguageDto> {

  private LanguageGroupMapper languageGroupMapper;

  public LanguageMapper(LanguageGroupMapper languageGroupMapper) {
    this.languageGroupMapper = languageGroupMapper;
  }

  @Override
  public LanguageDto toDto(Language language) {
    return toDto(language, false);
  }

  @Override
    public LanguageDto toDto(Language entity, boolean onlyActive) {
        if (entity == null) {
            LoggingUtils.logUnableToMapNullEntity(EntityTypeEnum.LANGUAGE, entity);
            throw new IllegalArgumentException("Null language cannot be mapped to dto");
        }

        LanguageDto.LanguageDtoBuilder languageDtoBuilder = LanguageDto.builder();
        LanguageGroupDto.LanguageGroupDtoBuilder languageGroupDtoBuilder = LanguageGroupDto.builder();

        LanguageGroupDto languageGroupDto = null;
        if (entity.getLanguageGroup() != null) {
            languageGroupDto = languageGroupDtoBuilder
                    .createdBy(entity.getLanguageGroup().getCreatedBy())
                    .id(entity.getLanguageGroup().getId())
                    .lastUpdated(entity.getLanguageGroup().getLastUpdated())
                    .name(entity.getLanguageGroup().getName())
                    .status(entity.getLanguageGroup().getStatus().name())
                    .updatedBy(entity.getLanguageGroup().getUpdatedBy())
                    .build();
        }

        String languageStatus = entity.getStatus() == null ? null : entity.getStatus().name();
        return languageDtoBuilder
                .constructed(convertYesNoChoiceToBool(entity.getConstructed()))
                .createdBy(entity.getCreatedBy())
                .deciphered(convertYesNoChoiceToBool(entity.getDeciphered()))
                .id(entity.getId())
                .iso6391Code1(entity.getIso6391Code1())
                .iso6392CodeAlpha2b(entity.getIso6392CodeAlpha2b())
                .iso6392CodeAlpha2t(entity.getIso6392CodeAlpha2t())
                .iso6392CodeAlpha3(entity.getIso6392CodeAlpha3())
                .languageGroup(languageGroupDto)
                .lastUpdated(entity.getLastUpdated())
                .living(convertYesNoChoiceToBool(entity.getLiving()))
                .macroLanguage(convertYesNoChoiceToBool(entity.getMacroLanguage()))
                .name(entity.getName())
                .nativeName(entity.getNativeName())
                .notes(entity.getNotes())
                .status(languageStatus)
                .updatedBy(entity.getUpdatedBy())
                .build();
    }

  @Override
  public Language toEntity(LanguageDto dto, boolean onlyActive) {
    if (dto == null) {
      LoggingUtils.logUnableToMapNullDto(EntityTypeEnum.LANGUAGE, dto);
      throw new IllegalArgumentException("Null language cannot be mapped to entity");
    }

    String constructed = dto.getConstructed() ? "Y" : "N";
    String deciphered = dto.getDeciphered() ? "Y" : "N";
    String living = dto.getLiving() ? "Y" : "N";
    String macroLanguage = dto.getMacroLanguage() ? "Y" : "N";
    LanguageGroup languageGroup = null;
    if (dto.getLanguageGroup() != null) {
      languageGroup = languageGroupMapper.toEntity(dto.getLanguageGroup());
    }
    return Language.builder()
      .constructed(YesNoChoice.getChoiceFor(constructed))
      .createdBy(dto.getCreatedBy())
      .deciphered(YesNoChoice.getChoiceFor(deciphered))
      .id(dto.getId())
      .iso6391Code1(dto.getIso6391Code1())
      .iso6392CodeAlpha2b(dto.getIso6392CodeAlpha2b())
      .iso6392CodeAlpha2t(dto.getIso6392CodeAlpha2t())
      .iso6392CodeAlpha3(dto.getIso6392CodeAlpha3())
      .languageGroup(languageGroup)
      .lastUpdated(dto.getLastUpdated())
      .living(YesNoChoice.getChoiceFor(living))
      .macroLanguage(YesNoChoice.getChoiceFor(macroLanguage))
      .name(dto.getName())
      .nativeName(dto.getNativeName())
      .notes(dto.getNotes())
      .status(VersionStatus.getInstance(dto.getStatus()))
      .updatedBy(dto.getUpdatedBy())
      .build();
  }

//  @Override
//  public Language toEntity(LanguageDto dto) {
//    return null;
//  }

  private boolean convertYesNoChoiceToBool(YesNoChoice yesNoChoice) {
        if (yesNoChoice == null || StringUtils.isEmpty(yesNoChoice.getCode())) {
            return false;
        }
        return BooleanUtils.toBoolean(yesNoChoice.getCode());
    }
}
