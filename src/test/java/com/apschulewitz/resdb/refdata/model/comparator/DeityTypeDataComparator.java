package com.apschulewitz.resdb.refdata.model.comparator;

import com.apschulewitz.resdb.common.model.validator.DataComparator;
import com.apschulewitz.resdb.refdata.model.dto.DeityTypeDto;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Objects;

@Component
public class DeityTypeDataComparator implements DataComparator<DeityTypeDto,Long> {

  @Override
  public boolean areEqual(DeityTypeDto dataEntity1, DeityTypeDto dataEntity2) {
    Objects.requireNonNull(dataEntity1);
    Objects.requireNonNull(dataEntity2);

    Objects.requireNonNull(dataEntity1.getName());
    Objects.requireNonNull(dataEntity2.getName());

    Comparator<DeityTypeDto> comparator = Comparator.comparing(DeityTypeDto::getName);

    return comparator.compare(dataEntity1, dataEntity2) == 0;
  }
}
