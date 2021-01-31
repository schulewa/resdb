package com.apschulewitz.resdb.refdata.model.comparator;

import com.apschulewitz.resdb.common.model.validator.DataComparator;
import com.apschulewitz.resdb.refdata.model.dto.CalendarTypeDto;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Objects;

@Component
public class CalendarTypeDataComparator implements DataComparator<CalendarTypeDto,Long> {

  @Override
  public boolean areEqual(CalendarTypeDto dataEntity1, CalendarTypeDto dataEntity2) {
    Objects.requireNonNull(dataEntity1);
    Objects.requireNonNull(dataEntity2);

    Comparator<CalendarTypeDto> comparator = Comparator.comparing(CalendarTypeDto::getName);

    return comparator.compare(dataEntity1, dataEntity2) == 0;
  }
}
