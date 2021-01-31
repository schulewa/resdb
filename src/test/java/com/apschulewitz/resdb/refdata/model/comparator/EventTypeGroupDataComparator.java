package com.apschulewitz.resdb.refdata.model.comparator;

import com.apschulewitz.resdb.common.model.validator.DataComparator;
import com.apschulewitz.resdb.refdata.model.dto.EntityTypeDto;
import com.apschulewitz.resdb.refdata.model.dto.EventTypeGroupDto;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Objects;

@Component
public class EventTypeGroupDataComparator implements DataComparator<EventTypeGroupDto,Long> {

  @Override
  public boolean areEqual(EventTypeGroupDto dataEntity1, EventTypeGroupDto dataEntity2) {
    Objects.requireNonNull(dataEntity1);
    Objects.requireNonNull(dataEntity2);

    Objects.requireNonNull(dataEntity1.getName());
    Objects.requireNonNull(dataEntity2.getName());

    Comparator<EventTypeGroupDto> comparator = Comparator.comparing(EventTypeGroupDto::getName);

    return comparator.compare(dataEntity1, dataEntity2) == 0;
  }
}
