package com.apschulewitz.resdb.refdata.model.comparator;

import com.apschulewitz.resdb.common.model.validator.DataComparator;
import com.apschulewitz.resdb.refdata.model.dto.RaceTypeDto;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Objects;

@Component
public class RaceTypeDataComparator implements DataComparator<RaceTypeDto,Long> {

  @Override
  public boolean areEqual(RaceTypeDto dataEntity1, RaceTypeDto dataEntity2) {
    Objects.requireNonNull(dataEntity1);
    Objects.requireNonNull(dataEntity2);

    Objects.requireNonNull(dataEntity1.getName());
    Objects.requireNonNull(dataEntity2.getName());

    Comparator<RaceTypeDto> comparator = Comparator.comparing(RaceTypeDto::getName);

    return comparator.compare(dataEntity1, dataEntity2) == 0;
  }
}
