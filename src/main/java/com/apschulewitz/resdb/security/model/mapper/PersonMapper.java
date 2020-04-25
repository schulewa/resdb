package com.apschulewitz.resdb.security.model.mapper;

import com.apschulewitz.resdb.refdata.model.entity.Person;
import com.apschulewitz.resdb.security.model.dto.HistoricalDateDto;
import com.apschulewitz.resdb.security.model.dto.PersonDto;
import com.apschulewitz.resdb.security.model.dto.PlaceDto;
import com.apschulewitz.resdb.security.model.dto.TitleDto;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper implements EntityMapper<Person, PersonDto> {

    @Override
    public PersonDto toDto(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Null person cannot be mapped to dto");
        }

        PlaceDto birthPlace = null;
        if (person.getBirthPlace() != null) {
            birthPlace = PlaceDto.builder()
                    .createdBy(person.getBirthPlace().getCreatedBy())
                    .id(person.getBirthPlace().getId())
                    .name(person.getBirthPlace().getName())
                    .status(person.getBirthPlace().getStatus().name())
                    .build();
        }

        PlaceDto deathPlace = null;
        if (person.getDeathPlace() != null) {
            deathPlace = PlaceDto.builder()
                    .createdBy(person.getDeathPlace().getCreatedBy())
                    .id(person.getDeathPlace().getId())
                    .name(person.getDeathPlace().getName())
                    .status(person.getDeathPlace().getStatus().name())
                    .build();
        }

        HistoricalDateDto birthDate = null;
        if (person.getDateOfBirth() != null) {
            birthDate = HistoricalDateDto.builder()
                    .day(person.getDateOfBirth().getDay())
                    .month(person.getDateOfBirth().getMonth())
                    .year(person.getDateOfBirth().getYear())
                    .build();
        }

        HistoricalDateDto deathDate = null;
        if (person.getDateOfDeath() != null) {
            deathDate = HistoricalDateDto.builder()
                    .day(person.getDateOfDeath().getDay())
                    .month(person.getDateOfDeath().getMonth())
                    .year(person.getDateOfDeath().getYear())
                    .build();
        }

        String gender = null;
        if (person.getAppliesTo() != null) {
            gender = person.getAppliesTo().name();
        }

        String prefixTitleAppliesTo = null;
        TitleDto prefixTitle = null;
        if (person.getPrefixTitle() != null) {
            if (person.getPrefixTitle().getAppliesTo() != null) {
                prefixTitleAppliesTo = person.getPrefixTitle().getAppliesTo().name();
            }

            prefixTitle = TitleDto.builder()
                    .appliesTo(prefixTitleAppliesTo)
                    .build();
        }

        String suffixTitleAppliesTo = null;
        TitleDto suffixTitle = null;
        if (person.getSuffixTitle() != null) {
            if (person.getSuffixTitle().getAppliesTo() != null) {
                suffixTitleAppliesTo = person.getSuffixTitle().getAppliesTo().name();
            }

            suffixTitle = TitleDto.builder()
                    .appliesTo(suffixTitleAppliesTo)
                    .build();
        }

        return PersonDto.builder()
                .birthPlace(birthPlace)
                .createdBy(person.getCreatedBy())
                .dateOfBirth(birthDate)
                .dateOfDeath(deathDate)
                .deathPlace(deathPlace)
                .familyName(person.getFamilyName())
                .firstName(person.getFirstName())
                .appliesTo(gender)
                .id(person.getId())
                .middleName(person.getMiddleName())
                .prefixTitle(prefixTitle)
                .status(person.getStatus().name())
                .suffixTitle(suffixTitle)
                .updatedBy(person.getUpdatedBy())
                .build();
    }

    @Override
    public PersonDto toDto(Person person, boolean onlyActive) {
        return null;
    }
}
