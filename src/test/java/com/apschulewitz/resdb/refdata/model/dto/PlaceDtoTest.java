package com.apschulewitz.resdb.refdata.model.dto;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static org.junit.Assert.assertNotNull;

@Slf4j
public class PlaceDtoTest {

  static Gson GSON = new Gson();

  @Test
  public void testAdd() {
    PlaceDto dto = PlaceDto.builder()
      .name("new Place")
      .build();
    log.info("new dto = " + GSON.toJson(dto));
    assertNotNull(dto);
  }

  @Test
  public void testUpdate() {
    PlaceDto dto = PlaceDto.builder()
      .createdBy("user1")
      .id(123L)
      .name("updated Place")
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .status(VersionStatus.Amend.name())
      .updatedBy("user2")
      .versionNumber(2L)
      .build();
    log.info("updated dto = " + GSON.toJson(dto));
    assertNotNull(dto);
  }
}
