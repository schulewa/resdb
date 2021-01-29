package com.apschulewitz.resdb.refdata.model.comparator;

import com.apschulewitz.resdb.common.model.validator.DataComparator;
import com.apschulewitz.resdb.refdata.model.dto.EntityTypeDto;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Objects;

@Component
public class EntityTypeDataComparator implements DataComparator<EntityTypeDto,Long> {

  @Override
  public boolean areEqual(EntityTypeDto dataEntity1, EntityTypeDto dataEntity2) {
    Objects.requireNonNull(dataEntity1);
    Objects.requireNonNull(dataEntity2);

    Objects.requireNonNull(dataEntity1.getName());
    Objects.requireNonNull(dataEntity2.getName());

    Comparator<EntityTypeDto> comparator = Comparator.comparing(EntityTypeDto::getName);

    return comparator.compare(dataEntity1, dataEntity2) == 0;
  }
}
