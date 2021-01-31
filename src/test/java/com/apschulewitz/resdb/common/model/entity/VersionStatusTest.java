package com.apschulewitz.resdb.common.model.entity;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;

public class VersionStatusTest {

  @Test
  public void given_New_code_when_getInstance_is_executed_then_return_version_status() {
    // Given
    String code = "N";

    // When
    VersionStatus versionStatus = VersionStatus.getInstance(code);

    // Then
    assertNotNull("VersionStatus not null", versionStatus);
    assertEquals("VersionStatus New", VersionStatus.New, versionStatus);
  }

  @Test
  public void given_Amend_code_when_getInstance_is_executed_then_return_version_status() {
    // Given
    String code = "A";

    // When
    VersionStatus versionStatus = VersionStatus.getInstance(code);

    // Then
    assertNotNull("VersionStatus not null", versionStatus);
    assertEquals("VersionStatus New", VersionStatus.Amend, versionStatus);
  }

  @Test
  public void given_Cancel_code_when_getInstance_is_executed_then_return_version_status() {
    // Given
    String code = "C";

    // When
    VersionStatus versionStatus = VersionStatus.getInstance(code);

    // Then
    assertNotNull("VersionStatus not null", versionStatus);
    assertEquals("VersionStatus New", VersionStatus.Cancel, versionStatus);
  }

  @Test
  public void given_unknown_code_when_getInstance_is_executed_then_return_null() {

    assertThatThrownBy(() -> VersionStatus.getInstance("^"))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Invalid code or name supplied as VersionStatus");

  }

  @Test
  public void given_New_version_status_when_isActive_is_executed_then_return_true() {
    // Given
    VersionStatus versionStatus = VersionStatus.New;

    // When
    boolean isActive = VersionStatus.isActive(versionStatus);

    // Then
    assertTrue("VersionStatus New isActive", isActive);
  }

  @Test
  public void given_Amend_version_status_when_isActive_is_executed_then_return_true() {
    // Given
    VersionStatus versionStatus = VersionStatus.Amend;

    // When
    boolean isActive = VersionStatus.isActive(versionStatus);

    // Then
    assertTrue("VersionStatus Amend isActive", isActive);
  }

  @Test
  public void given_Cancel_version_status_when_isActive_is_executed_then_return_false() {
    // Given
    VersionStatus versionStatus = VersionStatus.Cancel;

    // When
    boolean isActive = VersionStatus.isActive(versionStatus);

    // Then
    assertFalse("VersionStatus Cancel isActive", isActive);
  }

}
