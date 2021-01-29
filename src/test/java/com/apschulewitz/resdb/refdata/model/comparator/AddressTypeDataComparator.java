package com.apschulewitz.resdb.refdata.model.comparator;

import com.apschulewitz.resdb.common.model.validator.DataComparator;
import com.apschulewitz.resdb.refdata.model.dto.AddressTypeDto;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Objects;

@Component
public class AddressTypeDataComparator implements DataComparator<AddressTypeDto,Long> {

  @Override
  public boolean areEqual(AddressTypeDto dataEntity1, AddressTypeDto dataEntity2) {
    Objects.requireNonNull(dataEntity1);
    Objects.requireNonNull(dataEntity2);

    Comparator<AddressTypeDto> comparator = Comparator.comparing(AddressTypeDto::getName);

    return comparator.compare(dataEntity1, dataEntity2) == 0;
  }
}
