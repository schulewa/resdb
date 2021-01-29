package com.apschulewitz.resdb.refdata.model.comparator;

import com.apschulewitz.resdb.common.model.validator.DataComparator;
import com.apschulewitz.resdb.refdata.model.dto.CountryDto;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Objects;

@Component
public class CountryDataComparator implements DataComparator<CountryDto,Long> {

  @Override
  public boolean areEqual(CountryDto dataEntity1, CountryDto dataEntity2) {
    Objects.requireNonNull(dataEntity1);
    Objects.requireNonNull(dataEntity2);

    Objects.requireNonNull(dataEntity1.getCode());
    Objects.requireNonNull(dataEntity2.getCode());

    Objects.requireNonNull(dataEntity1.getName());
    Objects.requireNonNull(dataEntity2.getName());

    Comparator<CountryDto> comparator = Comparator.comparing(CountryDto::getCode)
                                                  .thenComparing(CountryDto::getName);

    return comparator.compare(dataEntity1, dataEntity2) == 0;
  }
}
