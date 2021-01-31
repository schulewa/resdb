package com.apschulewitz.resdb.refdata.model.comparator;

import com.apschulewitz.resdb.common.model.validator.DataComparator;
import com.apschulewitz.resdb.refdata.model.dto.ImageTypeDto;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Objects;

@Component
public class ImageTypeDataComparator implements DataComparator<ImageTypeDto,Long> {

  @Override
  public boolean areEqual(ImageTypeDto dataEntity1, ImageTypeDto dataEntity2) {
    Objects.requireNonNull(dataEntity1);
    Objects.requireNonNull(dataEntity2);

    Objects.requireNonNull(dataEntity1.getName());
    Objects.requireNonNull(dataEntity2.getName());

    Comparator<ImageTypeDto> comparator = Comparator.comparing(ImageTypeDto::getName);

    return comparator.compare(dataEntity1, dataEntity2) == 0;
  }
}
