package com.apschulewitz.resdb.refdata.model.dto;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static org.junit.Assert.assertNotNull;

@Slf4j
public class ArtefactGroupDtoTest {

  static Gson GSON = new Gson();

  @Test
  public void testAdd() {
    ArtefactGroupDto dto = ArtefactGroupDto.builder()
      .name("new Artefact group name")
      .build();
    log.info("new dto = " + GSON.toJson(dto));
    assertNotNull(dto);
  }

  @Test
  public void testUpdate() {
    ArtefactGroupDto dto = ArtefactGroupDto.builder()
      .createdBy("user1")
      .id(123L)
      .name("updated Artefact group name")
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .status(VersionStatus.Amend.name())
      .updatedBy("user2")
      .versionNumber(2L)
      .build();
    log.info("updated dto = " + GSON.toJson(dto));
    assertNotNull(dto);
  }
}
