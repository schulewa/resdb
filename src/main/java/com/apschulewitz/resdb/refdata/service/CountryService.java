package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.service.AbstractService;
import com.apschulewitz.resdb.common.service.DataService;
import com.apschulewitz.resdb.refdata.model.dao.CountryDao;
import com.apschulewitz.resdb.refdata.model.dto.CountryDto;
import com.apschulewitz.resdb.refdata.model.entity.Country;
import com.apschulewitz.resdb.refdata.model.mapper.CountryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CountryService
  extends AbstractService<CountryDto, Country, Long, CountryMapper, CountryDao>
  implements DataService<CountryDto, Country, Long> {

  private CountryDao countryDao;

  private CountryMapper countryMapper;

  public CountryService(CountryDao countryDao, CountryMapper countryMapper) {
    this.countryDao = countryDao;
    this.countryMapper = countryMapper;
  }

  public CountryDto add(CountryDto dto) {
    return add(EntityTypeEnum.COUNTRY, countryMapper, countryDao, dto);
  }

  public CountryDto deleteById(Long id) {
    return deleteById(EntityTypeEnum.COUNTRY, countryMapper, countryDao, id);
  }

  public List<CountryDto> findAll(boolean onlyActive) {
    return findAll(EntityTypeEnum.COUNTRY, countryMapper, countryDao, onlyActive);
  }

  public CountryDto findById(Long id) {
    return findById(EntityTypeEnum.COUNTRY, countryMapper, countryDao, id);
  }

  public CountryDto update(CountryDto dto) {
    return update(EntityTypeEnum.COUNTRY, countryMapper, countryDao, dto);
  }
}
