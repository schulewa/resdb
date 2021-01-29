package com.apschulewitz.resdb.refdata.model.comparator;

import com.apschulewitz.resdb.common.model.validator.DataComparator;
import com.apschulewitz.resdb.refdata.model.dto.EventTypeDto;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Objects;

@Component
public class EventTypeDataComparator implements DataComparator<EventTypeDto,Long> {

  @Override
  public boolean areEqual(EventTypeDto dataEntity1, EventTypeDto dataEntity2) {
    Objects.requireNonNull(dataEntity1);
    Objects.requireNonNull(dataEntity2);

    Objects.requireNonNull(dataEntity1.getName());
    Objects.requireNonNull(dataEntity2.getName());

    Comparator<EventTypeDto> comparator = Comparator.comparing(EventTypeDto::getName);

    return comparator.compare(dataEntity1, dataEntity2) == 0;
  }
}
