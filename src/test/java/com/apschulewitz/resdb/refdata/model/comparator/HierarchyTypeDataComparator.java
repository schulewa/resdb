package com.apschulewitz.resdb.refdata.model.comparator;

import com.apschulewitz.resdb.common.model.validator.DataComparator;
import com.apschulewitz.resdb.refdata.model.dto.HierarchyTypeDto;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Objects;

@Component
public class HierarchyTypeDataComparator implements DataComparator<HierarchyTypeDto,Long> {

  @Override
  public boolean areEqual(HierarchyTypeDto dataEntity1, HierarchyTypeDto dataEntity2) {
    Objects.requireNonNull(dataEntity1);
    Objects.requireNonNull(dataEntity2);

    Objects.requireNonNull(dataEntity1.getName());
    Objects.requireNonNull(dataEntity2.getName());

    Comparator<HierarchyTypeDto> comparator = Comparator.comparing(HierarchyTypeDto::getName);

    return comparator.compare(dataEntity1, dataEntity2) == 0;
  }
}
