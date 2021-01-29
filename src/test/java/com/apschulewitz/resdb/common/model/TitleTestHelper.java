package com.apschulewitz.resdb.common.model;

import com.apschulewitz.resdb.common.model.entity.Gender;
import com.apschulewitz.resdb.common.model.entity.Title;
import com.apschulewitz.resdb.common.model.entity.TitleType;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.TitleDto;
import com.apschulewitz.resdb.research.model.AbstractTestHelper;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

@Component
public class TitleTestHelper extends AbstractTestHelper<Title, TitleDto>  {

  @Override
  public Title constructUnsavedMinimalEntity() {
    return Title.builder()
      .appliesTo(Gender.Male)
      .titleType(TitleType.Prefix)
      .title("Sir")
      .build();
  }

  @Override
  public Title constructNewEntityWithAllValues() {
    return Title.builder()
      .appliesTo(Gender.Male)
      .createdBy(USER_NAME)
      .description("Description for title Sir")
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .status(VersionStatus.Amend)
      .titleType(TitleType.Prefix)
      .title("Sir")
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public TitleDto constructUnsavedMinimalDto() {
    return TitleDto.builder()
      .appliesTo(Gender.Male.name())
      .title("Sir")
      .titleType(TitleType.Prefix.name())
      .build();
  }

  @Override
  public TitleDto constructNewDtoWithAllValues() {
    return TitleDto.builder()
      .appliesTo(Gender.Male.name())
      .createdBy(USER_NAME)
      .description("Description for title Sir")
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .status(VersionStatus.Amend.name())
      .title("Sir")
      .titleType(TitleType.Prefix.name())
      .updatedBy(USER_NAME1)
      .versionNumber(ID.getAndIncrement())
      .build();
  }

  @Override
  public List<TitleDto> constructListOfUnsavedMinimalDto() {
    return null;
  }
}
