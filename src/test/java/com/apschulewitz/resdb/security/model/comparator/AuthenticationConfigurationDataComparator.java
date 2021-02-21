package com.apschulewitz.resdb.security.model.comparator;

import com.apschulewitz.resdb.common.model.validator.DataComparator;
import com.apschulewitz.resdb.security.model.dto.AuthenticationConfigurationDto;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Objects;

@Component
public class AuthenticationConfigurationDataComparator implements DataComparator<AuthenticationConfigurationDto,Long> {

  @Override
  public boolean areEqual(AuthenticationConfigurationDto dataEntity1, AuthenticationConfigurationDto dataEntity2) {
    Objects.requireNonNull(dataEntity1);
    Objects.requireNonNull(dataEntity2);

    Comparator<AuthenticationConfigurationDto> comparator = Comparator.comparing(AuthenticationConfigurationDto::getMaximumPasswordAgeInDays)
      .thenComparing(AuthenticationConfigurationDto::getMaximumPasswordLength)
      .thenComparing(AuthenticationConfigurationDto::getMinimumLowercaseCharacters)
      .thenComparing(AuthenticationConfigurationDto::getMinimumNumberCharacters)
      .thenComparing(AuthenticationConfigurationDto::getMinimumPasswordLength)
      .thenComparing(AuthenticationConfigurationDto::getMinimumSpecialCharacters)
      .thenComparing(AuthenticationConfigurationDto::getMinimumUppercaseCharacters);

    return comparator.compare(dataEntity1, dataEntity2) == 0;
  }
}
