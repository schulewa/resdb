package com.apschulewitz.resdb.common.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

public class ConfigurationPropertyNameTest {

  @Test
  public void given_resdb_db_conn_checker_when_getForCode_is_executed_then_return_ConfigurationPropertyName() {
    // given

    // when
    ConfigurationPropertyName configurationPropertyName = ConfigurationPropertyName.getForName("resdb.db.conn.checker");

    // then
    assertEquals(ConfigurationPropertyName.DATABASE_CONNECTION_CHECKER, configurationPropertyName);
  }

  @Test
  public void given_resdb_db_dataLocation_when_getForCode_is_executed_then_return_ConfigurationPropertyName() {
    // given

    // when
    ConfigurationPropertyName configurationPropertyName = ConfigurationPropertyName.getForName("resdb.db.dataLocation");

    // then
    assertEquals(ConfigurationPropertyName.DATABASE_DATA_LOCATION, configurationPropertyName);
  }

  @Test
  public void given_resdb_db_driver_when_getForCode_is_executed_then_return_ConfigurationPropertyName() {
    // given

    // when
    ConfigurationPropertyName configurationPropertyName = ConfigurationPropertyName.getForName("resdb.db.driver");

    // then
    assertEquals(ConfigurationPropertyName.DATABASE_DRIVER, configurationPropertyName);
  }

  @Test
  public void given_resdb_db_driver_exception_sorter_when_getForCode_is_executed_then_return_ConfigurationPropertyName() {
    // given

    // when
    ConfigurationPropertyName configurationPropertyName = ConfigurationPropertyName.getForName("resdb.db.driver.exception.sorter");

    // then
    assertEquals(ConfigurationPropertyName.DATABASE_DRIVER_EXCEPTION_SORTER, configurationPropertyName);
  }

  @Test
  public void given_resdb_db_initLocation_when_getForCode_is_executed_then_return_ConfigurationPropertyName() {
    // given

    // when
    ConfigurationPropertyName configurationPropertyName = ConfigurationPropertyName.getForName("resdb.db.initLocation");

    // then
    assertEquals(ConfigurationPropertyName.DATABASE_INIT_LOCATION, configurationPropertyName);
  }

  @Test
  public void given_resdb_db_password_when_getForCode_is_executed_then_return_ConfigurationPropertyName() {
    // given

    // when
    ConfigurationPropertyName configurationPropertyName = ConfigurationPropertyName.getForName("resdb.db.password");

    // then
    assertEquals(ConfigurationPropertyName.DATABASE_PASSWORD, configurationPropertyName);
  }

  @Test
  public void given_resdb_db_type_when_getForCode_is_executed_then_return_ConfigurationPropertyName() {
    // given

    // when
    ConfigurationPropertyName configurationPropertyName = ConfigurationPropertyName.getForName("resdb.db.type");

    // then
    assertEquals(ConfigurationPropertyName.DATABASE_TYPE, configurationPropertyName);
  }

  @Test
  public void given_resdb_db_url_when_getForCode_is_executed_then_return_ConfigurationPropertyName() {
    // given

    // when
    ConfigurationPropertyName configurationPropertyName = ConfigurationPropertyName.getForName("resdb.db.url");

    // then
    assertEquals(ConfigurationPropertyName.DATABASE_URL, configurationPropertyName);
  }

  @Test
  public void given_resdb_db_user_when_getForCode_is_executed_then_return_ConfigurationPropertyName() {
    // given

    // when
    ConfigurationPropertyName configurationPropertyName = ConfigurationPropertyName.getForName("resdb.db.user");

    // then
    assertEquals(ConfigurationPropertyName.DATABASE_USER, configurationPropertyName);
  }

  @Test
  public void given_resdb_db_jpa_packages_to_scan_when_getForCode_is_executed_then_return_ConfigurationPropertyName() {
    // given

    // when
    ConfigurationPropertyName configurationPropertyName = ConfigurationPropertyName.getForName("resdb.db.jpa-packages-to-scan");

    // then
    assertEquals(ConfigurationPropertyName.JPA_ENTITY_PACKAGES_TO_SCAN, configurationPropertyName);
  }

  @Test
  public void given_resdb_db_entity_manager_factory_class_when_getForCode_is_executed_then_return_ConfigurationPropertyName() {
    // given

    // when
    ConfigurationPropertyName configurationPropertyName = ConfigurationPropertyName.getForName("resdb.db.entity-manager-factory-class");

    // then
    assertEquals(ConfigurationPropertyName.JPA_ENTITY_MANAGER_FACTORY_CLASS, configurationPropertyName);
  }

  @Test
  public void given_resdb_jpa_persistence_unit_name_when_getForCode_is_executed_then_return_ConfigurationPropertyName() {
    // given

    // when
    ConfigurationPropertyName configurationPropertyName = ConfigurationPropertyName.getForName("resdb.jpa.persistence-unit-name");

    // then
    assertEquals(ConfigurationPropertyName.JPA_PERSISTENCE_UNIT_NAME, configurationPropertyName);
  }

  @Test
  public void given_resdb_jpa_provider_dialect_when_getForCode_is_executed_then_return_ConfigurationPropertyName() {
    // given

    // when
    ConfigurationPropertyName configurationPropertyName = ConfigurationPropertyName.getForName("resdb.jpa.provider.dialect");

    // then
    assertEquals(ConfigurationPropertyName.JPA_PROVIDER_DIALECT, configurationPropertyName);
  }

  @Test
  public void given_resdb_jpa_provider_format_sql_when_getForCode_is_executed_then_return_ConfigurationPropertyName() {
    // given

    // when
    ConfigurationPropertyName configurationPropertyName = ConfigurationPropertyName.getForName("resdb.jpa.provider.format_sql");

    // then
    assertEquals(ConfigurationPropertyName.JPA_PROVIDER_FORMAT_SQL, configurationPropertyName);
  }

  @Test
  public void given_resdb_jpa_provider_hbm2ddl_when_getForCode_is_executed_then_return_ConfigurationPropertyName() {
    // given

    // when
    ConfigurationPropertyName configurationPropertyName = ConfigurationPropertyName.getForName("resdb.jpa.provider.hbm2ddl");

    // then
    assertEquals(ConfigurationPropertyName.JPA_PROVIDER_HBM2DDL, configurationPropertyName);
  }

  @Test
  public void given_resdb_jpa_provider_show_sql_when_getForCode_is_executed_then_return_ConfigurationPropertyName() {
    // given

    // when
    ConfigurationPropertyName configurationPropertyName = ConfigurationPropertyName.getForName("resdb.jpa.provider.show_sql");

    // then
    assertEquals(ConfigurationPropertyName.JPA_PROVIDER_SHOW_SQL, configurationPropertyName);
  }

  @Test
  public void given_resdb_db_jpa_vendor_adapter_class_when_getForCode_is_executed_then_return_ConfigurationPropertyName() {
    // given

    // when
    ConfigurationPropertyName configurationPropertyName = ConfigurationPropertyName.getForName("resdb.db.jpa-vendor-adapter.class");

    // then
    assertEquals(ConfigurationPropertyName.JPA_VENDOR_ADAPTER_CLASS, configurationPropertyName);
  }

  @Test
  public void given_resdb_jpa_vendor_adapter_database_platform_when_getForCode_is_executed_then_return_ConfigurationPropertyName() {
    // given

    // when
    ConfigurationPropertyName configurationPropertyName = ConfigurationPropertyName.getForName("resdb.jpa-vendor-adapter.database-platform");

    // then
    assertEquals(ConfigurationPropertyName.JPA_VENDOR_ADAPTER_DATABASE_PLATFORM, configurationPropertyName);
  }

  @Test
  public void given_resdb_jpa_provider_generate_ddl_when_getForCode_is_executed_then_return_ConfigurationPropertyName() {
    // given

    // when
    ConfigurationPropertyName configurationPropertyName = ConfigurationPropertyName.getForName("resdb.jpa.provider.generate-ddl");

    // then
    assertEquals(ConfigurationPropertyName.JPA_PROVIDER_GENERATE_DDL, configurationPropertyName);
  }

  @Test
  public void given_resdb_jpa_vendor_adapter_show_sql_when_getForCode_is_executed_then_return_ConfigurationPropertyName() {
    // given

    // when
    ConfigurationPropertyName configurationPropertyName = ConfigurationPropertyName.getForName("resdb.jpa-vendor-adapter.show-sql");

    // then
    assertEquals(ConfigurationPropertyName.JPA_VENDOR_ADAPTER_SHOW_SQL, configurationPropertyName);
  }

  @Test
  public void given_resdb_pool_jndi_name_when_getForCode_is_executed_then_return_ConfigurationPropertyName() {
    // given

    // when
    ConfigurationPropertyName configurationPropertyName = ConfigurationPropertyName.getForName("resdb.pool.jndi.name");

    // then
    assertEquals(ConfigurationPropertyName.POOL_JNDI_NAME, configurationPropertyName);
  }

  @Test
  public void given_resdb_pool_size_maximum_when_getForCode_is_executed_then_return_ConfigurationPropertyName() {
    // given

    // when
    ConfigurationPropertyName configurationPropertyName = ConfigurationPropertyName.getForName("resdb.pool.size.maximum");

    // then
    assertEquals(ConfigurationPropertyName.POOL_SIZE_MAXIMUM, configurationPropertyName);
  }

  @Test
  public void given_unknown_code_when_getForCode_is_executed_then_return_error() {
    assertThatThrownBy(() -> ConfigurationPropertyName.getForName("xxx"))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageStartingWith("No ConfigurationPropertyName found for name");
  }

  @Test
  public void given_empty_name_when_getForCode_is_executed_then_return_error() {
    assertThatThrownBy(() -> ConfigurationPropertyName.getForName(""))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageStartingWith("No ConfigurationPropertyName found for name");
  }

  @Test
  public void given_null_code_when_getForCode_is_executed_then_return_error() {
    assertThatThrownBy(() -> ConfigurationPropertyName.getForName(null))
      .isInstanceOf(NullPointerException.class);
  }
}
