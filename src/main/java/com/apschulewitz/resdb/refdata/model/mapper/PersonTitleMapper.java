package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.TitleMapper;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.refdata.model.dto.AddressTypeDto;
import com.apschulewitz.resdb.refdata.model.dto.PersonTitleDto;
import com.apschulewitz.resdb.refdata.model.entity.AddressType;
import com.apschulewitz.resdb.refdata.model.entity.PersonTitle;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class PersonTitleMapper implements VersionableEntityDtoMapper<PersonTitle, PersonTitleDto> {

  private PersonMapper personMapper;

  private TitleMapper titleMapper;

  public PersonTitleMapper(PersonMapper personMapper, TitleMapper titleMapper) {
    this.personMapper = personMapper;
    this.titleMapper = titleMapper;
  }

  @Override
  public PersonTitle toEntity(PersonTitleDto dto) {

    return PersonTitle.builder()
      .id(dto.getId())
      .person(personMapper.toEntity(dto.getPerson()))
      .position(dto.getPosition())
      .title(titleMapper.toEntity(dto.getTitle()))
      .build();
  }

  @Override
  public PersonTitleDto toDto(PersonTitle entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Null person title cannot be mapped to dto");
    }

    return PersonTitleDto.builder()
      .id(entity.getId())
      .person(personMapper.toDto(entity.getPerson()))
      .position(entity.getPosition())
      .title(titleMapper.toDto(entity.getTitle()))
      .build();
  }

  @Override
  public PersonTitleDto toDto(PersonTitle entity, boolean onlyActive) {
//    if (VersionStatus.getLiveStatuses().contains(entity.getStatus()) || !onlyActive) {
//      return toDto(entity);
//    }
    return toDto(entity);
  }

  @Override
  public PersonTitle toEntity(PersonTitleDto dto, boolean onlyActive) {
    return toEntity(dto);
  }

//  @Override
//  public Person toDto(PersonDto entity) {
//    return null;
//  }
}
