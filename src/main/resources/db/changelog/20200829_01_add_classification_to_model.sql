--liquibase formatted sql

--changeset author:schulewa

--comment add resdb_classification_collection and resdb_classification_entry to data model

CREATE TABLE resdb_classification_collection
(
    id           BIGINT      NOT NULL AUTO_INCREMENT,
    name         VARCHAR(50) NOT NULL,
    status       VARCHAR(1)  NOT NULL,
    created_by   VARCHAR(20) NOT NULL,
    last_updated TIMESTAMP   NULL,
    updated_by   VARCHAR(20) NULL,
    CONSTRAINT resdb_classification_collection_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_classification_collection_name_idx ON resdb_classification_collection (name);

CREATE TABLE resdb_classification_entry
(
    id              BIGINT      NOT NULL AUTO_INCREMENT,
    name            VARCHAR(50) NOT NULL,
    collection_id   BIGINT      NOT NULL,
    parent_entry_id BIGINT      NULL,
    status          VARCHAR(1)  NOT NULL,
    created_by      VARCHAR(20) NOT NULL,
    last_updated    TIMESTAMP   NULL,
    updated_by      VARCHAR(20) NULL,
    CONSTRAINT resdb_classification_entry_pk PRIMARY KEY (id)
);
CREATE INDEX resdb_classification_entry_idx1 ON resdb_classification_entry (collection_id, name);
