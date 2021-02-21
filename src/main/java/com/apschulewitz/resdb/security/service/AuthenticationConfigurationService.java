package com.apschulewitz.resdb.security.service;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.service.AbstractService;
import com.apschulewitz.resdb.common.service.DataService;
import com.apschulewitz.resdb.security.model.dao.AuthenticationConfigurationDao;
import com.apschulewitz.resdb.security.model.dto.AuthenticationConfigurationDto;
import com.apschulewitz.resdb.security.model.entity.AuthenticationConfiguration;
import com.apschulewitz.resdb.security.model.mapper.AuthenticationConfigurationMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationConfigurationService
  extends AbstractService<AuthenticationConfigurationDto, AuthenticationConfiguration, Long,
  AuthenticationConfigurationMapper, AuthenticationConfigurationDao>
  implements DataService<AuthenticationConfigurationDto, AuthenticationConfiguration, Long> {

  private AuthenticationConfigurationDao authenticationConfigurationDao;

  private AuthenticationConfigurationMapper authenticationConfigurationMapper;

  public AuthenticationConfigurationService(AuthenticationConfigurationDao authenticationConfigurationDao,
                                            AuthenticationConfigurationMapper authenticationConfigurationMapper) {
    this.authenticationConfigurationDao = authenticationConfigurationDao;
    this.authenticationConfigurationMapper = authenticationConfigurationMapper;
  }

  @Override
  public AuthenticationConfigurationDto add(AuthenticationConfigurationDto dto) {
    return add(EntityTypeEnum.AUTHENTICATION_CONFIGURATION,
                authenticationConfigurationMapper, authenticationConfigurationDao, dto);
  }

  @Override
  public AuthenticationConfigurationDto deleteById(Long id) {
    return deleteById(EntityTypeEnum.AUTHENTICATION_CONFIGURATION,
                      authenticationConfigurationMapper, authenticationConfigurationDao, id);
  }

  @Override
  public List<AuthenticationConfigurationDto> findAll(boolean onlyActive) {
    return findAll(EntityTypeEnum.AUTHENTICATION_CONFIGURATION,
                    authenticationConfigurationMapper, authenticationConfigurationDao, onlyActive);
  }

  @Override
  public AuthenticationConfigurationDto findById(Long id) {
    return findById(EntityTypeEnum.AUTHENTICATION_CONFIGURATION,
                    authenticationConfigurationMapper, authenticationConfigurationDao, id);
  }

  @Override
  public AuthenticationConfigurationDto update(AuthenticationConfigurationDto dto) {
    return update(EntityTypeEnum.AUTHENTICATION_CONFIGURATION,
                  authenticationConfigurationMapper, authenticationConfigurationDao, dto);
  }

}
