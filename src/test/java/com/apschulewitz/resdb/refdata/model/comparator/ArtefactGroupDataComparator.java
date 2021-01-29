package com.apschulewitz.resdb.refdata.model.comparator;

import com.apschulewitz.resdb.common.model.validator.DataComparator;
import com.apschulewitz.resdb.refdata.model.dto.ArtefactGroupDto;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Objects;

@Component
public class ArtefactGroupDataComparator implements DataComparator<ArtefactGroupDto,Long> {

  @Override
  public boolean areEqual(ArtefactGroupDto dataEntity1, ArtefactGroupDto dataEntity2) {
    Objects.requireNonNull(dataEntity1);
    Objects.requireNonNull(dataEntity2);

    Comparator<ArtefactGroupDto> comparator = Comparator.comparing(ArtefactGroupDto::getName);

    return comparator.compare(dataEntity1, dataEntity2) == 0;
  }
}
