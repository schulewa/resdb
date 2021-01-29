package com.apschulewitz.resdb.refdata.model.comparator;

import com.apschulewitz.resdb.common.model.validator.DataComparator;
import com.apschulewitz.resdb.refdata.model.dto.AddressTypeDto;
import com.apschulewitz.resdb.refdata.model.dto.MeasureTypeDto;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Objects;

@Component
public class MeasureTypeDataComparator implements DataComparator<MeasureTypeDto,Long> {

  @Override
  public boolean areEqual(MeasureTypeDto dataEntity1, MeasureTypeDto dataEntity2) {
    Objects.requireNonNull(dataEntity1);
    Objects.requireNonNull(dataEntity2);

    Comparator<MeasureTypeDto> comparator = Comparator.comparing(MeasureTypeDto::getName);

    return comparator.compare(dataEntity1, dataEntity2) == 0;
  }
}
