package com.apschulewitz.resdb.refdata.model.helper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.entity.YesNoChoice;
import com.apschulewitz.resdb.refdata.model.dto.LanguageDto;
import com.apschulewitz.resdb.refdata.model.dto.LanguageGroupDto;
import com.apschulewitz.resdb.refdata.model.entity.Language;
import com.apschulewitz.resdb.refdata.model.entity.LanguageGroup;
import com.apschulewitz.resdb.research.model.AbstractTestHelper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LanguageTestHelper extends AbstractTestHelper<Language, LanguageDto> {

  @Override
  public Language constructUnsavedMinimalEntity() {
    return Language.builder()
      .constructed(YesNoChoice.No)
      .deciphered(YesNoChoice.No)
      .living(YesNoChoice.No)
      .macroLanguage(YesNoChoice.No)
      .iso6391Code1("1")
      .iso6392CodeAlpha2b("2b")
      .iso6392CodeAlpha2t("2t")
      .iso6392CodeAlpha3("3")
      .name(Language.class.getSimpleName())
      .nativeName(Language.class.getSimpleName()+"-native")
      .build();
  }

  @Override
  public Language constructNewEntityWithAllValues() {
    return Language.builder()
      .constructed(YesNoChoice.Yes)
      .createdBy(USER_NAME)
      .deciphered(YesNoChoice.No)
      .id(ID.getAndIncrement())
      .iso6391Code1("1")
      .iso6392CodeAlpha2b("2b")
      .iso6392CodeAlpha2t("2t")
      .iso6392CodeAlpha3("3")
      .languageGroup(LanguageGroup.builder().createdBy(USER_NAME).name("Language group").id(1L).status(VersionStatus.New).versionNumber(0L).build())
      .living(YesNoChoice.No)
      .macroLanguage(YesNoChoice.Yes)
      .name(Language.class.getSimpleName())
      .nativeName("native")
      .notes("notes")
      .status(VersionStatus.Amend)
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public LanguageDto constructUnsavedMinimalDto() {
    return LanguageDto.builder()
      .iso6391Code1("01")
      .iso6392CodeAlpha2b("2b")
      .iso6392CodeAlpha2t("2t")
      .iso6392CodeAlpha3("3")
      .name(LanguageDto.class.getSimpleName())
      .nativeName(LanguageDto.class.getSimpleName()+"-native")
      .constructed(false)
      .living(false)
      .macroLanguage(false)
      .deciphered(false)
      .build();
  }

  @Override
  public LanguageDto constructNewDtoWithAllValues() {
    return LanguageDto.builder()
      .constructed(true)
      .createdBy(USER_NAME)
      .deciphered(true)
      .id(ID.getAndIncrement())
      .iso6391Code1("iso6391Code1")
      .iso6392CodeAlpha2b("iso6392CodeAlpha2b")
      .iso6392CodeAlpha2t("iso6392CodeAlpha2t")
      .iso6392CodeAlpha3("iso6392CodeAlpha3")
      .languageGroup(LanguageGroupDto.builder().createdBy(USER_NAME).name(LanguageGroupDto.class.getSimpleName()).status(VersionStatus.New.name()).versionNumber(VERSION_NUMBER.getAndIncrement()).build())
      .living(true)
      .macroLanguage(true)
      .name(LanguageDto.class.getSimpleName())
      .nativeName("Native")
      .notes("Notes")
      .status(VersionStatus.Amend.name())
      .build();
  }

  @Override
  public List<LanguageDto> constructListOfUnsavedMinimalDto() {
    return null;
  }
}
