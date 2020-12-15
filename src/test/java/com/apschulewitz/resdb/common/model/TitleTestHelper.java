package com.apschulewitz.resdb.common.model;

import com.apschulewitz.resdb.common.model.entity.Gender;
import com.apschulewitz.resdb.common.model.entity.Title;
import com.apschulewitz.resdb.common.model.entity.TitleType;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.TitleDto;
import com.apschulewitz.resdb.research.model.AbstractTestHelper;
import org.springframework.stereotype.Component;

@Component
public class TitleTestHelper extends AbstractTestHelper  {

  public static Title constructNewTitle(String title, TitleType titleType, Gender gender) {
    return Title.builder()
      .appliesTo(gender)
      .createdBy(USER_NAME)
      .status(VersionStatus.New)
      .titleType(titleType)
      .title(title)
      .build();
  }

  public static TitleDto constructNewTitleDto(String title, TitleType titleType, Gender gender) {
    return TitleDto.builder()
      .appliesTo(gender.name())
      .createdBy(USER_NAME)
      .status(VersionStatus.New.name())
      .titleType(titleType.name())
      .title(title)
      .build();
  }
}
