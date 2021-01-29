package com.apschulewitz.resdb.refdata.model.comparator;

import com.apschulewitz.resdb.common.model.validator.DataComparator;
import com.apschulewitz.resdb.refdata.model.dto.ArtefactTypeDto;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Objects;

@Component
public class ArtefactTypeDataComparator implements DataComparator<ArtefactTypeDto,Long> {

  @Override
  public boolean areEqual(ArtefactTypeDto dataEntity1, ArtefactTypeDto dataEntity2) {
    Objects.requireNonNull(dataEntity1);
    Objects.requireNonNull(dataEntity2);

    Comparator<ArtefactTypeDto> comparator = Comparator.comparing(ArtefactTypeDto::getName);

    return comparator.compare(dataEntity1, dataEntity2) == 0;
  }
}
