package com.apschulewitz.resdb.common;

import com.apschulewitz.resdb.config.model.dao.SystemParameterDao;
import com.apschulewitz.resdb.config.model.entity.SystemParameter;
import com.apschulewitz.resdb.config.model.entity.SystemParameterTypes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Configuration
public class SystemConfiguration {

  private final Map<String, String> stringSystemParameters;
  private final Map<String, Integer> integerSystemParameters;
  private final Map<String, Boolean> booleanSystemParameters;

  public SystemConfiguration(SystemParameterDao systemParameterDao) {

    List<SystemParameter> parameters = loadSystemParameters(systemParameterDao);

    stringSystemParameters = parameters.stream()
      .filter(p -> !StringUtils.isEmpty(p.getStringValue()))
      .collect(Collectors.toUnmodifiableMap(p -> p.getName(), p -> p.getStringValue()));

    integerSystemParameters = parameters.stream()
      .filter(p -> p.getIntegerValue() != null)
      .collect(Collectors.toUnmodifiableMap(p -> p.getName(), p -> p.getIntegerValue()));

    booleanSystemParameters = parameters.stream()
      .filter(p -> p.getBooleanValue() != null)
      .collect(Collectors.toUnmodifiableMap(p -> p.getName(), p -> p.getBooleanValue()));
  }

  @Bean
  public Boolean sendUserVerificationEmailWhenRegistering() {
    return Optional.ofNullable(getBooleanParameter(SystemParameterTypes.SEND_USER_VERIFICATION_EMAIL_WHEN_REGISTERING)).orElse(false);
  }

  public Boolean getBooleanParameter(SystemParameterTypes parameterType) {
    return Optional.ofNullable(booleanSystemParameters.get(parameterType.getName())).orElse(null);
  }

  public Integer getIntegerParameter(SystemParameterTypes parameterType) {
    return Optional.ofNullable(integerSystemParameters.get(parameterType.getName())).orElse(null);
  }

  public String getStringParameter(SystemParameterTypes parameterType) {
    return Optional.ofNullable(stringSystemParameters.get(parameterType.getName())).orElse(null);
  }

  private List<SystemParameter> loadSystemParameters(SystemParameterDao systemParameterDao) {
    Iterable<SystemParameter> iter = systemParameterDao.findAll();
    return StreamSupport.stream(iter.spliterator(), false).collect(Collectors.toList());
  }
}
