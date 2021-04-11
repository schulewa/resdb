--liquibase formatted sql

--changeset author:schulewa

ALTER TABLE resdb_user_account ADD email VARCHAR(100) NOT NULL;
ALTER TABLE resdb_user_account ADD email_verified TIMESTAMP NULL;
ALTER TABLE resdb_user_account ADD recovery_email VARCHAR(100) NULL;
ALTER TABLE resdb_user_account ADD recovery_email_verified TIMESTAMP NULL;

UPDATE TABLE resdb_user_account SET email = '';

ALTER TABLE resdb_user_password MODIFY password VARCHAR(100);

CREATE TABLE resdb_auth_config
(
    id                       BIGINT NOT NULL AUTO_INCREMENT,
    min_password_len         INT    NOT NULL,
    max_password_len         INT    NOT NULL,
    min_lowercase            INT    NOT NULL,
    min_uppercase            INT    NOT NULL,
    min_number               INT    NOT NULL,
    min_special              INT    NOT NULL,
    max_password_age_in_days INT    NOT NULL
);

INSERT INTO resdb_auth_config VALUES (8, 30, 1, 1, 1, 1, 90);

CREATE TABLE resdb_user_registration
(
    id                  BIGINT       NOT NULL AUTO_INCREMENT,
    email               VARCHAR(100) NOT NULL,
    verification_code   VARCHAR(50)  NULL,
    date_verified       TIMESTAMP    NULL,
    first_name          VARCHAR(30)  NOT NULL,
    family_name         VARCHAR(50)  NOT NULL,
    registration_status CHAR(1)      NOT NULL,
    version_no          BIGINT       NOT NULL,
    status              VARCHAR(1)   NOT NULL,
    created_by          VARCHAR(20)  NOT NULL,
    last_updated        TIMESTAMP    NULL,
    updated_by          VARCHAR(20)  NULL,
    CONSTRAINT resdb_user_registration_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_user_registration_idx ON resdb_user_account (email);
CREATE UNIQUE INDEX resdb_user_registration_idx2 ON resdb_user_account (verification_code);

INSERT INTO resdb_system_parameters (name, description, boolean_value)
VALUES ('SEND_USER_VERIFICATION_EMAIL_WHEN_REGISTERING', 'If set to true then a verification email will automatically be sent to the user being registered, else is sent on user being accepted.', 0);
