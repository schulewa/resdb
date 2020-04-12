package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.refdata.model.entity.Language;
import com.apschulewitz.resdb.security.model.dto.LanguageDto;
import com.apschulewitz.resdb.security.model.dto.LanguageGroupDto;
import com.apschulewitz.resdb.security.model.mapper.EntityMapper;
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
            languageGroupDtoBuilder
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
                .iso6391Code(language.getIso6391Code())
                .iso6392CodeAlpha2(language.getIso6392CodeAlpha2())
                .iso6392CodeAlpha3B(language.getIso6392CodeAlpha3B())
                .iso6392CodeAlpha3t(language.getIso6392CodeAlpha3t())
                .languageGroup(languageGroupDto)
                .build();
    }
}
