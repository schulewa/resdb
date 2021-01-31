package com.apschulewitz.resdb.refdata.model.comparator;

import com.apschulewitz.resdb.common.model.validator.DataComparator;
import com.apschulewitz.resdb.refdata.model.dto.ImageTypeDto;
import com.apschulewitz.resdb.refdata.model.dto.LanguageGroupDto;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Objects;

@Component
public class LanguageGroupDataComparator implements DataComparator<LanguageGroupDto,Long> {

  @Override
  public boolean areEqual(LanguageGroupDto dataEntity1, LanguageGroupDto dataEntity2) {
    Objects.requireNonNull(dataEntity1);
    Objects.requireNonNull(dataEntity2);

    Objects.requireNonNull(dataEntity1.getName());
    Objects.requireNonNull(dataEntity2.getName());

    Comparator<LanguageGroupDto> comparator = Comparator.comparing(LanguageGroupDto::getName);

    return comparator.compare(dataEntity1, dataEntity2) == 0;
  }
}
