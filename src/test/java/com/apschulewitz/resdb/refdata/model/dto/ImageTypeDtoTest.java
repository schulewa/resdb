package com.apschulewitz.resdb.refdata.model.dto;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static org.junit.Assert.assertNotNull;

@Slf4j
public class ImageTypeDtoTest {

  static Gson GSON = new Gson();

  @Test
  public void testAdd() {
    ImageTypeDto dto = ImageTypeDto.builder()
      .name("new Image type")
      .build();
    log.info("new dto = " + GSON.toJson(dto));
    assertNotNull(dto);
  }

  @Test
  public void testUpdate() {
    ImageTypeDto dto = ImageTypeDto.builder()
      .createdBy("user1")
      .defaultHeight(90)
      .defaultWidth(15)
      .id(123L)
      .name("updated Image type")
      .numberOfDimenions(2)
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .status(VersionStatus.Amend.name())
      .updatedBy("user2")
      .versionNumber(2L)
      .build();
    log.info("updated dto = " + GSON.toJson(dto));
    assertNotNull(dto);
  }
}
