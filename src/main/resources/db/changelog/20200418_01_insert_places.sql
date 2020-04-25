--liquibase formatted sql

--changeset author:schulewa

--comment add places for Sir Winston Churchill in order to test data relationships

/*
    name      VARCHAR(30) NOT NULL,
    latitude  VARCHAR(10) NULL,
    longitude VARCHAR(10) NULL,
    altitude  VARCHAR(10) NULL,
    river_id  BIGINT      NULL,
    status        VARCHAR(1)   NOT NULL,
    created_by    VARCHAR(20)  NOT NULL
 */

insert resdb_place (name, status, created_by)
values ('Blenheim Palace', 'A', 'admin');

insert resdb_place (name, status, created_by)
values ('London', 'A', 'admin');
