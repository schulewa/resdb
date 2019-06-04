package com.apschulewitz.resdb.common.model;

/**
 * Created by adrianschulewitz on 16/08/2016.
 */
public enum ConfigurationPropertyNames {

    DATABASE_CONNECTION_CHECKER("resdb.db.conn.checker"),
    DATABASE_CONNECTION_IDLE_TIMEOUT_MINUTES("resdb.db.conn.idle.timeout.minutes"),
    DATABASE_DATA_LOCATION("resdb.db.dataLocation"),
    DATABASE_DRIVER("resdb.db.driver"),
    DATABASE_DRIVER_EXCEPTION_SORTER("resdb.db.driver.exception.sorter"),
    DATABASE_INIT_LOCATION("resdb.db.initLocation"),
    DATABASE_PASSWORD("resdb.db.password"),
    DATABASE_TYPE("resdb.db.type"),
    DATABASE_URL("resdb.db.url"),
    DATABASE_USER("resdb.db.user"),
    JPA_ENTITY_PACKAGES_TO_SCAN("resdb.db.jpa-packages-to-scan"),
    JPA_ENTITY_MANAGER_FACTORY_CLASS("resdb.db.entity-manager-factory-class"),
    JPA_PERSISTENCE_UNIT_NAME("resdb.jpa.persistence-unit-name"),
    JPA_PROVIDER_DIALECT("resdb.jpa.provider.dialect"),
    JPA_PROVIDER_FORMAT_SQL("resdb.jpa.provider.format_sql"),
    JPA_PROVIDER_HBM2DDL("resdb.jpa.provider.hbm2ddl"),
    JPA_PROVIDER_SHOW_SQL("resdb.jpa.provider.show_sql"),
    JPA_VENDOR_ADAPTER_CLASS("resdb.db.jpa-vendor-adapter.class"),
    JPA_VENDOR_ADAPTER_DATABASE_PLATFORM("resdb.jpa-vendor-adapter.database-platform"),
    JPA_PROVIDER_GENERATE_DDL("resdb.jpa.provider.generate-ddl"),
    JPA_VENDOR_ADAPTER_SHOW_SQL("resdb.jpa-vendor-adapter.show-sql"),
    POOL_JNDI_NAME("resdb.pool.jndi.name"),
    POOL_SIZE_MAXIMUM("resdb.pool.size.maximum "),
    POOL_SIZE_MINIMUM("resdb.pool.size.minimum ");

    private ConfigurationPropertyNames(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
