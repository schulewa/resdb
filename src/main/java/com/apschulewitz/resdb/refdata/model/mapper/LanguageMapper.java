package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.entity.YesNoChoice;
import com.apschulewitz.resdb.common.utils.StringUtils;
import com.apschulewitz.resdb.refdata.model.entity.Language;
import com.apschulewitz.resdb.security.model.dto.LanguageDto;
import com.apschulewitz.resdb.security.model.dto.LanguageGroupDto;
import com.apschulewitz.resdb.security.model.mapper.EntityMapper;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Component;

@Component
public class LanguageMapper implements EntityMapper<Language, LanguageDto> {

    @Override
    public LanguageDto toDto(Language language) {
        return toDto(language, false);
    }

    @Override
    public LanguageDto toDto(Language language, boolean onlyActive) {
        if (language == null) {
            throw new IllegalArgumentException("Null user account cannot be mapped to dto");
        }

        LanguageDto.LanguageDtoBuilder languageDtoBuilder = LanguageDto.builder();
        LanguageGroupDto.LanguageGroupDtoBuilder languageGroupDtoBuilder = LanguageGroupDto.builder();

        LanguageGroupDto languageGroupDto = null;
        if (language.getLanguageGroup() != null) {
            languageGroupDto = languageGroupDtoBuilder
                    .createdBy(language.getLanguageGroup().getCreatedBy())
                    .id(language.getLanguageGroup().getId())
                    .lastUpdated(language.getLanguageGroup().getLastUpdated())
                    .name(language.getLanguageGroup().getName())
                    .status(language.getLanguageGroup().getStatus().name())
                    .updatedBy(language.getLanguageGroup().getUpdatedBy())
                    .build();
        }

        return languageDtoBuilder
                .id(language.getId())
                .name(language.getName())
                .nativeName(language.getNativeName())
                .iso6391Code1(language.getIso6391Code1())
                .iso6392CodeAlpha2b(language.getIso6392CodeAlpha2b())
                .iso6392CodeAlpha2t(language.getIso6392CodeAlpha2t())
                .iso6392CodeAlpha3(language.getIso6392CodeAlpha3())
                .deciphered(convertYesNoChoiceToBool(language.getDeciphered()))
                .living(convertYesNoChoiceToBool(language.getLiving()))
                .constructed(convertYesNoChoiceToBool(language.getConstructed()))
                .macroLanguage(convertYesNoChoiceToBool(language.getMacroLanguage()))
                .languageGroup(languageGroupDto)
                .notes(language.getNotes())
                .status(language.getStatus())
                .createdBy(language.getCreatedBy())
                .lastUpdated(language.getLastUpdated())
                .updatedBy(language.getUpdatedBy())
                .build();
    }

    private boolean convertYesNoChoiceToBool(YesNoChoice yesNoChoice) {
        if (yesNoChoice == null || StringUtils.isEmpty(yesNoChoice.getCode())) {
            return false;
        }
        return BooleanUtils.toBoolean(yesNoChoice.getCode());
    }
}
