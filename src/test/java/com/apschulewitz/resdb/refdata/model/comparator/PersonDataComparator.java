package com.apschulewitz.resdb.refdata.model.comparator;

import com.apschulewitz.resdb.common.model.validator.DataComparator;
import com.apschulewitz.resdb.refdata.model.dto.PersonDto;
import com.apschulewitz.resdb.refdata.model.dto.PersonTypeDto;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Objects;

@Component
public class PersonDataComparator implements DataComparator<PersonDto,Long> {

  @Override
  public boolean areEqual(PersonDto dataEntity1, PersonDto dataEntity2) {
    Objects.requireNonNull(dataEntity1);
    Objects.requireNonNull(dataEntity2);

    Comparator<PersonDto> comparator = Comparator.comparing(PersonDto::getFirstName)
                                                  .thenComparing(PersonDto::getFamilyName);

    return comparator.compare(dataEntity1, dataEntity2) == 0;
  }
}
