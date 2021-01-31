package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.service.AbstractService;
import com.apschulewitz.resdb.common.service.DataService;
import com.apschulewitz.resdb.refdata.model.dao.AddressTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.AddressTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.AddressType;
import com.apschulewitz.resdb.refdata.model.mapper.AddressTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class AddressTypeService
  extends AbstractService<AddressTypeDto, AddressType, Long, AddressTypeMapper, AddressTypeDao>
  implements DataService<AddressTypeDto, AddressType, Long> {

  private AddressTypeDao addressTypeDao;

  private AddressTypeMapper addressTypeMapper;

  public AddressTypeService(AddressTypeDao addressTypeDao, AddressTypeMapper addressTypeMapper) {
    this.addressTypeDao = addressTypeDao;
    this.addressTypeMapper = addressTypeMapper;
  }

  public AddressTypeDto add(AddressTypeDto dto) {
    return add(EntityTypeEnum.ADDRESS_TYPE, addressTypeMapper, addressTypeDao, dto);
  }

  public AddressTypeDto deleteById(Long id) {
    return deleteById(EntityTypeEnum.ADDRESS_TYPE, addressTypeMapper, addressTypeDao, id);
  }

  public List<AddressTypeDto> findAll(boolean onlyActive) {
    return findAll(EntityTypeEnum.ADDRESS_TYPE, addressTypeMapper, addressTypeDao, onlyActive);
  }

  public AddressTypeDto findById(Long id) {
    return findById(EntityTypeEnum.ADDRESS_TYPE, addressTypeMapper, addressTypeDao, id);
  }

  public AddressTypeDto update(AddressTypeDto dto) {
    return update(EntityTypeEnum.ADDRESS_TYPE, addressTypeMapper, addressTypeDao, dto);
  }
}
