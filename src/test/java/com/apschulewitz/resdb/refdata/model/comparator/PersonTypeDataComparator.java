package com.apschulewitz.resdb.refdata.model.comparator;

import com.apschulewitz.resdb.common.model.validator.DataComparator;
import com.apschulewitz.resdb.refdata.model.dto.PersonTypeDto;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Objects;

@Component
public class PersonTypeDataComparator implements DataComparator<PersonTypeDto,Long> {

  @Override
  public boolean areEqual(PersonTypeDto dataEntity1, PersonTypeDto dataEntity2) {
    Objects.requireNonNull(dataEntity1);
    Objects.requireNonNull(dataEntity2);

    Comparator<PersonTypeDto> comparator = Comparator.comparing(PersonTypeDto::getName);

    return comparator.compare(dataEntity1, dataEntity2) == 0;
  }
}
