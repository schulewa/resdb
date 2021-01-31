package com.apschulewitz.resdb.refdata.model.comparator;

import com.apschulewitz.resdb.common.model.validator.DataComparator;
import com.apschulewitz.resdb.refdata.model.dto.RaceTypeDto;
import com.apschulewitz.resdb.research.model.dto.ClassificationCollectionDto;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Objects;

@Component
public class ClassificationCollectionDataComparator implements DataComparator<ClassificationCollectionDto,Long> {

  @Override
  public boolean areEqual(ClassificationCollectionDto dataEntity1, ClassificationCollectionDto dataEntity2) {
    Objects.requireNonNull(dataEntity1);
    Objects.requireNonNull(dataEntity2);

    Objects.requireNonNull(dataEntity1.getName());
    Objects.requireNonNull(dataEntity2.getName());

    Comparator<ClassificationCollectionDto> comparator = Comparator.comparing(ClassificationCollectionDto::getName);

    return comparator.compare(dataEntity1, dataEntity2) == 0;
  }
}
