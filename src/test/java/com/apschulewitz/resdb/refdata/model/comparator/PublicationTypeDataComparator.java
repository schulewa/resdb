package com.apschulewitz.resdb.refdata.model.comparator;

import com.apschulewitz.resdb.common.model.validator.DataComparator;
import com.apschulewitz.resdb.refdata.model.dto.LanguageGroupDto;
import com.apschulewitz.resdb.refdata.model.dto.PublicationTypeDto;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Objects;

@Component
public class PublicationTypeDataComparator implements DataComparator<PublicationTypeDto,Long> {

  @Override
  public boolean areEqual(PublicationTypeDto dataEntity1, PublicationTypeDto dataEntity2) {
    Objects.requireNonNull(dataEntity1);
    Objects.requireNonNull(dataEntity2);

    Objects.requireNonNull(dataEntity1.getName());
    Objects.requireNonNull(dataEntity2.getName());

    Comparator<PublicationTypeDto> comparator = Comparator.comparing(PublicationTypeDto::getName);

    return comparator.compare(dataEntity1, dataEntity2) == 0;
  }
}
