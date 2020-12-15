--liquibase formatted sql

--changeset author:schulewa

CREATE TABLE resdb_address_type
(
    id           BIGINT      NOT NULL AUTO_INCREMENT,
    name         VARCHAR(30) NOT NULL,
    version_no   BIGINT      NOT NULL,
    status       VARCHAR(1)  NOT NULL,
    created_by   VARCHAR(20) NOT NULL,
    last_updated TIMESTAMP   NULL,
    updated_by   VARCHAR(20) NULL,
    CONSTRAINT resdb_address_type_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_address_type_name_idx ON resdb_address_type (name);

CREATE TABLE resdb_application_version
(
    id              BIGINT       NOT NULL AUTO_INCREMENT,
    major_version   INTEGER      NOT NULL,
    minor_version   INTEGER      NOT NULL,
    bug_fix_version INTEGER      NOT NULL,
    description     VARCHAR(100) NULL,
    CONSTRAINT resdb_application_version_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_application_version_idx ON resdb_application_version (major_version, minor_version, bug_fix_version);

CREATE TABLE resdb_artefact
(
    id                  BIGINT       NOT NULL AUTO_INCREMENT,
    name                VARCHAR(30)  NOT NULL,
    description         VARCHAR(250) NULL,
    date_found_year     INTEGER      NULL,
    date_found_month    INTEGER      NULL,
    date_found_day      INTEGER      NULL,
    artefact_type_id    BIGINT       NOT NULL,
    found_by_person_id  BIGINT       NULL,
    artefact_group_id   BIGINT       NULL,
    owner_identifier    VARCHAR(10)  NULL,
    technology_id       BIGINT       NULL,
    composition_id      BIGINT       NULL,
    version_no          BIGINT      NOT NULL,
    status              VARCHAR(1)  NOT NULL,
    created_by          VARCHAR(20) NOT NULL,
    last_updated        TIMESTAMP   NULL,
    updated_by          VARCHAR(20) NULL,
    CONSTRAINT resdb_artefact_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_artefact_name_idx ON resdb_artefact (name);


CREATE TABLE resdb_artefact_attribute
(
    id                         BIGINT NOT NULL AUTO_INCREMENT,
    artefact_id                BIGINT NOT NULL,
    artefact_attribute_type_id BIGINT NOT NULL,
    CONSTRAINT resdb_artefact_attribute_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_artefact_attribute_idx ON resdb_artefact_attribute (artefact_id, artefact_attribute_type_id);


CREATE TABLE resdb_artefact_attribute_type
(
    id                        BIGINT      NOT NULL AUTO_INCREMENT,
    name                      VARCHAR(20) NOT NULL,
    data_type                 VARCHAR(20) NOT NULL,
    is_mandatory              BIT         NOT NULL,
    data_class_association_id BIGINT      NULL,
    CONSTRAINT resdb_artefact_attr_type_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_artfct_atrib_typ_nam_idx ON resdb_artefact_attribute_type (name);


CREATE TABLE resdb_artefact_group
(
    id   BIGINT      NOT NULL AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    status       VARCHAR(1)  NOT NULL,
    created_by   VARCHAR(20) NOT NULL,
    last_updated TIMESTAMP   NULL,
    updated_by   VARCHAR(20) NULL,
    CONSTRAINT resdb_artefact_group_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_artefact_group_name_idx ON resdb_address_type (name);


CREATE TABLE resdb_artefact_image
(
    id                         BIGINT NOT NULL AUTO_INCREMENT,
    artefact_id                BIGINT NOT NULL,
    image_collection_header_id BIGINT NOT NULL,
    CONSTRAINT resdb_artefact_image_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_artefact_image_idx ON resdb_artefact_image (artefact_id, image_collection_header_id);


CREATE TABLE resdb_artefact_img_pttrn_smbl
(
    id                BIGINT NOT NULL AUTO_INCREMENT,
    artefact_image_id BIGINT NOT NULL,
    pattern_symbol_id BIGINT NOT NULL,
    CONSTRAINT resdb_artfct_img_ptrn_smbl_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_artfct_img_ptrn_smbl_idx ON resdb_artefact_img_pttrn_smbl (artefact_image_id, pattern_symbol_id);


CREATE TABLE resdb_artefact_place
(
    id                BIGINT NOT NULL AUTO_INCREMENT,
    artefact_id       BIGINT NOT NULL,
    place_id          BIGINT NOT NULL,
    held_at_entity_id BIGINT NOT NULL,
    CONSTRAINT resdb_artefact_place_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_artefact_place_idx ON resdb_artefact_place (artefact_id, place_id);


CREATE TABLE resdb_artefact_rstrtn_work
(
    id                  BIGINT       NOT NULL AUTO_INCREMENT,
    description         VARCHAR(250) NULL,
    artefact_id         INTEGER      NOT NULL,
    date_started_year   INTEGER      NULL,
    date_started_month  INTEGER      NULL,
    date_started_day    INTEGER      NULL,
    date_finished_year  INTEGER      NULL,
    date_finished_month INTEGER      NULL,
    CONSTRAINT resdb_artfct_rstrtn_wrk_pk PRIMARY KEY (id)
);


CREATE TABLE resdb_artefact_type
(
    id                  BIGINT      NOT NULL AUTO_INCREMENT,
    name                VARCHAR(20) NOT NULL,
    version_no          BIGINT      NOT NULL,
    status              VARCHAR(1)  NOT NULL,
    created_by          VARCHAR(20) NOT NULL,
    last_updated        TIMESTAMP   NULL,
    updated_by          VARCHAR(20) NULL,
    CONSTRAINT resdb_artefact_type_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_artfct_typ_nam_idx on resdb_artefact_type (name);

CREATE TABLE resdb_attribute_type
(
    id           BIGINT NOT NULL AUTO_INCREMENT,
    name         VARCHAR(50) NOT NULL,
    status       VARCHAR(1)  NOT NULL,
    created_by   VARCHAR(20) NOT NULL,
    last_updated TIMESTAMP   NULL,
    updated_by   VARCHAR(20) NULL,
    CONSTRAINT resdb_attribute_type_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_attribute_type_idx ON resdb_attribute_type (name);

CREATE TABLE resdb_bibliography
(
    id   BIGINT      NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    CONSTRAINT resdb_bibliography_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_bibliography_name_idx ON resdb_bibliography (name);


CREATE TABLE resdb_bibliography_publication
(
    id              BIGINT NOT NULL AUTO_INCREMENT,
    bibliography_id BIGINT NOT NULL,
    publication_id  BIGINT NOT NULL,
    CONSTRAINT resdb_bibliography_publctn_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_bibliography_publctn_idx ON resdb_bibliography_publication (bibliography_id, publication_id);


CREATE TABLE resdb_calendar
(
    id                BIGINT       NOT NULL AUTO_INCREMENT,
    name              VARCHAR(50)  NOT NULL,
    calendar_type_id  BIGINT       NOT NULL,
    calendar_group_id BIGINT       NOT NULL,
    notes             VARCHAR(500) NULL,
    owned_by          VARCHAR(30)  NOT NULL,
    status            VARCHAR(1)   NOT NULL,
    created_by        VARCHAR(20)  NOT NULL,
    last_updated      TIMESTAMP    NULL,
    updated_by        VARCHAR(20)  NULL,
        CONSTRAINT resdb_calendar_pk PRIMARY KEY (id)
);
CREATE INDEX resdb_calendar_name_idx ON resdb_calendar (name);

CREATE TABLE resdb_calendar_group
(
    id       BIGINT      NOT NULL AUTO_INCREMENT,
    name     VARCHAR(30) NOT NULL,
    owned_by VARCHAR(30) NOT NULL,
    CONSTRAINT resdb_calendar_group_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_calendar_group_name_idx ON resdb_calendar_group (name);

CREATE TABLE resdb_calendar_race
(
    id          BIGINT      NOT NULL AUTO_INCREMENT,
    calendar_id BIGINT      NOT NULL,
    race_id     INTEGER     NOT NULL,
    owned_by    VARCHAR(30) NOT NULL,
    CONSTRAINT resdb_calendar_race_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_calendar_race_idx ON resdb_calendar_race (calendar_id, race_id);


CREATE TABLE resdb_calendar_type
(
    id                     BIGINT      NOT NULL AUTO_INCREMENT,
    name                   VARCHAR(30) NOT NULL,
    days_per_leap_year     INTEGER     NULL,
    days_per_non_leap_year INTEGER     NULL,
    status                 VARCHAR(1)  NOT NULL,
    created_by             VARCHAR(20) NOT NULL,
    version_no             INTEGER     NOT NULL,
    last_updated           TIMESTAMP   NULL,
    updated_by             VARCHAR(20) NULL,
    CONSTRAINT resdb_calendar_type_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_calendar_type_name_idx ON resdb_calendar_type (name);


CREATE TABLE resdb_country
(
    id            BIGINT       NOT NULL AUTO_INCREMENT,
    code          VARCHAR(2)   NOT NULL,
    name          VARCHAR(75)  NOT NULL,
    state_name    VARCHAR(150) NULL,
    sovereignty   VARCHAR(50)  NULL,
    flag_image_id BIGINT       NULL,
    status        VARCHAR(1)   NOT NULL,
    created_by    VARCHAR(20)  NOT NULL,
    last_updated  TIMESTAMP    NULL,
    updated_by    VARCHAR(20)  NULL,
    CONSTRAINT resdb_country_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_country_code_idx ON resdb_country (code);
CREATE UNIQUE INDEX resdb_country_name_idx ON resdb_country (name);

-- CountryRegion class is an inter-section between Country and Region classes
CREATE TABLE resdb_country_region
(
    id   BIGINT      NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    CONSTRAINT resdb_country_region_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_country_region_name_idx ON resdb_country_region (name);


CREATE TABLE resdb_data_class
(
    id                         BIGINT       NOT NULL AUTO_INCREMENT,
    data_class_key             VARCHAR(30)  NOT NULL,
    fully_qualified_class_name VARCHAR(250) NOT NULL,
    CONSTRAINT resdb_data_class_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_data_class_name_idx ON resdb_data_class (data_class_key);


CREATE TABLE resdb_data_class_method
(
    id             BIGINT       NOT NULL AUTO_INCREMENT,
    data_class_key VARCHAR(30)  NOT NULL,
    method_name    VARCHAR(250) NOT NULL,
    CONSTRAINT resdb_data_class_method_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_data_class_method_idx ON resdb_data_class_method (data_class_key, method_name);


CREATE TABLE resdb_data_class_method_argmnt
(
    id                   BIGINT       NOT NULL AUTO_INCREMENT,
    data_class_key       VARCHAR(30)  NOT NULL,
    method_name          varchar(250) NOT NULL,
    argument_sequence_no INTEGER      NOT NULL,
    argument_data_type   VARCHAR(250) NOT NULL,
    CONSTRAINT resdb_data_clss_mthd_argmnt_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_data_clss_mthd_arg_idx ON resdb_data_class_method_argmnt (data_class_key, method_name, argument_sequence_no);


CREATE TABLE resdb_deity
(
    id   BIGINT      NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    CONSTRAINT resdb_deity_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_deity_name_idx ON resdb_deity (name);


CREATE TABLE resdb_deity_alias
(
    id            BIGINT NOT NULL AUTO_INCREMENT,
    from_deity_id BIGINT NOT NULL,
    to_alias_id   BIGINT NOT NULL,
    CONSTRAINT resdb_deity_alias_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_deity_alias_idx ON resdb_deity_alias (from_deity_id, to_alias_id);


CREATE TABLE resdb_deity_religion
(
    id                BIGINT NOT NULL AUTO_INCREMENT,
    deity_id          BIGINT NOT NULL,
    religion_alias_id BIGINT NOT NULL,
    CONSTRAINT resdb_deity_religion_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_deity_religion_idx ON resdb_deity_religion (deity_id, religion_alias_id);


CREATE TABLE resdb_deity_type
(
    id           BIGINT      NOT NULL AUTO_INCREMENT,
    name         VARCHAR(30) NOT NULL,
    status       VARCHAR(1)  NOT NULL,
    created_by   VARCHAR(20) NOT NULL,
    last_updated TIMESTAMP   NULL,
    updated_by   VARCHAR(20) NULL,
    CONSTRAINT resdb_deity_type_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_deity_type_name_idx ON resdb_deity_type (name);

CREATE TABLE resdb_dropdown_class
(
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    CONSTRAINT resdb_dropdown_class_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_dropdown_class_idx ON resdb_dropdown_class (name);

CREATE TABLE resdb_dropdown_domain
(
    id                INTEGER      NOT NULL AUTO_INCREMENT,
    short_code        VARCHAR(10)  NOT NULL,
    description       VARCHAR(100) NOT NULL,
    dropdown_class_id INTEGER      NOT NULL,
    CONSTRAINT resdb_dropdown_domain_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_dropdown_domain_idx ON resdb_dropdown_domain (short_code);

CREATE TABLE resdb_entity
(
    id             BIGINT      NOT NULL AUTO_INCREMENT,
    name           VARCHAR(30) NOT NULL,
    entity_type_id INTEGER     NOT NULL,
    CONSTRAINT resdb_entity_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_entity_name_idx ON resdb_entity (name);


CREATE TABLE resdb_entity_type
(
    id           BIGINT      NOT NULL AUTO_INCREMENT,
    name         VARCHAR(30) NOT NULL,
    status       VARCHAR(1)  NOT NULL,
    created_by   VARCHAR(20) NOT NULL,
    last_updated TIMESTAMP   NULL,
    updated_by   VARCHAR(20) NULL,
    CONSTRAINT resdb_entity_type_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_entity_type_name_idx ON resdb_entity_type (name);


CREATE TABLE resdb_entity_address
(
    id              BIGINT NOT NULL AUTO_INCREMENT,
    entity_id       BIGINT NOT NULL,
    address_type_id BIGINT NOT NULL,
    address_id      BIGINT NOT NULL,
    CONSTRAINT resdb_entity_address_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_entity_address_name_idx ON resdb_entity_address (entity_id, address_type_id, address_id);


CREATE TABLE resdb_event
(
    id               BIGINT       NOT NULL AUTO_INCREMENT,
    name             VARCHAR(30)  NOT NULL,
    event_type_id    BIGINT       NOT NULL,
    description      VARCHAR(250) NULL,
    event_date_year  INTEGER      NULL,
    event_date_month INTEGER      NULL,
    event_date_day   INTEGER      NULL,
    place_id         INTEGER      NULL,
    CONSTRAINT resdb_event_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_event_name_idx ON resdb_event (name);


CREATE TABLE resdb_event_person
(
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    event_id    BIGINT       NOT NULL,
    person_id   BIGINT       NOT NULL,
    involvement VARCHAR(250) NULL,
    CONSTRAINT resdb_event_person_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_event_person_name_idx ON resdb_event_person (event_id, person_id);


CREATE TABLE resdb_event_attribute
(
    id                      BIGINT NOT NULL AUTO_INCREMENT,
    event_id                BIGINT NOT NULL,
    event_attribute_type_id BIGINT NOT NULL,
    CONSTRAINT resdb_event_attribute_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_event_attribute_idx ON resdb_event_attribute (event_id, event_attribute_type_id);


CREATE TABLE resdb_event_attribute_type
(
    id                        BIGINT      NOT NULL AUTO_INCREMENT,
    event_id                  BIGINT      NOT NULL,
    name                      VARCHAR(20) NOT NULL,
    is_mandatory              BIT         NOT NULL,
    data_class_association_id BIGINT      NOT NULL,
    CONSTRAINT resdb_event_attribute_type_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_event_attribute_type_idx ON resdb_event_attribute_type (event_id, name);


CREATE TABLE resdb_event_type
(
    id                  BIGINT      NOT NULL AUTO_INCREMENT,
    name                VARCHAR(30) NOT NULL,
    event_type_group_id BIGINT      NOT NULL,
    status              VARCHAR(1)  NOT NULL,
    created_by          VARCHAR(20) NOT NULL,
    last_updated        TIMESTAMP   NULL,
    updated_by          VARCHAR(20) NULL,
    CONSTRAINT resdb_event_type_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_event_type_name_idx ON resdb_entity_type (name);


CREATE TABLE resdb_event_type_group
(
    id           BIGINT      NOT NULL AUTO_INCREMENT,
    name         VARCHAR(30) NOT NULL,
    status       VARCHAR(1)  NOT NULL,
    created_by   VARCHAR(20) NOT NULL,
    last_updated TIMESTAMP   NULL,
    updated_by   VARCHAR(20) NULL,
    CONSTRAINT resdb_event_type_group_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_event_type_group_name_idx ON resdb_event_type_group (name);


CREATE TABLE resdb_hierarchy
(
    id                BIGINT      NOT NULL AUTO_INCREMENT,
    name              VARCHAR(30) NOT NULL,
    hierarchy_type_id BIGINT      NOT NULL,
    version_no        INTEGER     NOT NULL,
    CONSTRAINT resdb_hierarchy_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_hierarchy_name_idx ON resdb_hierarchy (name);


CREATE TABLE resdb_hierarchy_node
(
    id                       BIGINT      NOT NULL AUTO_INCREMENT,
    name                     VARCHAR(30) NOT NULL,
    parent_hierarchy_id      BIGINT      NOT NULL,
    first_child_hierarchy_id BIGINT      NULL,
    last_child_hierarchy_id  BIGINT      NULL,
    leaf_node_ind            BIT         NOT NULL,
    CONSTRAINT resdb_hierarchy_node_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_hierarchy_node_name_idx ON resdb_hierarchy_node (name);


CREATE TABLE resdb_hierarchy_type
(
    id           BIGINT      NOT NULL AUTO_INCREMENT,
    name         VARCHAR(30) NOT NULL,
    status       VARCHAR(1)  NOT NULL,
    created_by   VARCHAR(20) NOT NULL,
    last_updated TIMESTAMP   NULL,
    updated_by   VARCHAR(20) NULL,
    CONSTRAINT resdb_hierarchy_type_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_hierarchy_type_name_idx ON resdb_hierarchy_type (name);


CREATE TABLE resdb_image
(
    id            BIGINT      NOT NULL AUTO_INCREMENT,
    file_path     VARCHAR(30) NOT NULL,
    file_name     VARCHAR(30) NOT NULL,
    image_type_id BIGINT      NOT NULL,
    CONSTRAINT resdb_image_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_image_idx ON resdb_image (file_path, file_name);


CREATE TABLE resdb_image_collection
(
    id                BIGINT      NOT NULL AUTO_INCREMENT,
    name              VARCHAR(30) NOT NULL,
    number_of_rows    INTEGER     NULL,
    number_of_columns INTEGER     NULL,
    CONSTRAINT resdb_image_collection_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_image_collection_name_idx ON resdb_image_collection (name);


create table resdb_image_collection_seq
(
    id                  BIGINT  NOT NULL AUTO_INCREMENT,
    image_collection_id BIGINT  NOT NULL,
    `row_number`          INTEGER NOT NULL,
    column_number       INTEGER NOT NULL,
    artefact_image_id   BIGINT  NOT NULL,
    CONSTRAINT resdb_image_collection_seq_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_image_collection_seq_idx ON resdb_image_collection_seq (image_collection_id, `row_number`, column_number);

CREATE TABLE resdb_image_type
(
    id               INTEGER     NOT NULL AUTO_INCREMENT,
    name             VARCHAR(20) NOT NULL,
    no_of_dimensions INTEGER     NOT NULL,
    default_width    INTEGER     NULL,
    default_height   INTEGER     NULL,
    status           VARCHAR(1)  NOT NULL,
    created_by       VARCHAR(20) NOT NULL,
    last_updated     TIMESTAMP   NULL,
    updated_by       VARCHAR(20) NULL,
    CONSTRAINT resdb_image_type_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_image_typ_nam_idx on resdb_image_type (name);

CREATE TABLE resdb_language
(
    id                     BIGINT        NOT NULL AUTO_INCREMENT,
    name                   VARCHAR(30)   NOT NULL,
    native_name            VARCHAR(1000) NOT NULL,
    iso_6391_code_1        VARCHAR(2)    NOT NULL,
    iso_6392_code_alpha_2t VARCHAR(3)    NOT NULL,
    iso_6392_code_alpha_2b VARCHAR(3)    NOT NULL,
    iso_6392_code_alpha_3  VARCHAR(8)    NOT NULL,
    deciphered             VARCHAR(1)    NOT NULL,
    living                 VARCHAR(1)    NOT NULL,
    constructed            VARCHAR(1)    NOT NULL,
    macro_Language         VARCHAR(1)    NOT NULL,
    language_group_id      BIGINT        NULL,
    notes                  VARCHAR(500)  NULL,
    status                 VARCHAR(1)    NOT NULL,
    created_by             VARCHAR(20)   NOT NULL,
    last_updated           TIMESTAMP     NULL,
    updated_by             VARCHAR(20)   NULL,
    CONSTRAINT resdb_language_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_language_name_idx ON resdb_language (name);


CREATE TABLE resdb_language_group
(
    id           BIGINT      NOT NULL AUTO_INCREMENT,
    name         VARCHAR(30) NOT NULL,
    region_id    BIGINT      NULL,
    status       VARCHAR(1)  NOT NULL,
    created_by   VARCHAR(20) NOT NULL,
    version_no   BIGINT      NOT NULL,
    last_updated TIMESTAMP   NULL,
    updated_by   VARCHAR(20) NULL,
    CONSTRAINT resdb_language_group_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX language_group_name_idx ON resdb_language_group (name);


CREATE TABLE resdb_language_pattern_symbol
(
    id                BIGINT NOT NULL AUTO_INCREMENT,
    language_id       BIGINT not null,
    pattern_symbol_id BIGINT not null,
    CONSTRAINT resdb_language_pttrn_smbl_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_language_pattern_symbol_idx ON resdb_language_pattern_symbol (language_id, pattern_symbol_id);


create table resdb_library_location
(
    id             BIGINT NOT NULL AUTO_INCREMENT,
    publication_id BIGINT NOT NULL,
    entity_id      BIGINT NOT NULL,
    CONSTRAINT resdb_library_location_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_library_location_idx ON resdb_library_location (publication_id, entity_id);

CREATE TABLE resdb_material
(
    id                   BIGINT      NOT NULL AUTO_INCREMENT,
    name                 VARCHAR(30) NOT NULL,
    from_measure_type_id BIGINT      NOT NULL,
    from_quantity        FLOAT       NOT NULL,
    to_measure_type_id   BIGINT      NOT NULL,
    to_quantity          FLOAT       NOT NULL,
    CONSTRAINT resdb_material_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_material_name_idx ON resdb_material (name);

create table resdb_measure
(
    id                      BIGINT       NOT NULL AUTO_INCREMENT,
    name                    VARCHAR(30)  NOT NULL,
    measure_type_id         BIGINT       NOT NULL,
    associated_with_race_id BIGINT       NULL,
    description             VARCHAR(500) NULL,
    status                  VARCHAR(1)   NOT NULL,
    created_by              VARCHAR(20)  NOT NULL,
    last_updated            TIMESTAMP    NULL,
    updated_by              VARCHAR(20)  NULL,
    CONSTRAINT resdb_measure_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_measure_name_idx ON resdb_measure (name, measure_type_id);

create table resdb_measure_converter
(
    id              BIGINT      NOT NULL AUTO_INCREMENT,
    name            VARCHAR(30) NOT NULL,
    from_measure_id BIGINT      NOT NULL,
    from_quantity   BIGINT      NOT NULL,
    to_measure_id   BIGINT      NOT NULL,
    to_quantity     FLOAT       NOT NULL,
    math_operator   CHAR(10)    NOT NULL,
    approximation   CHAR(1)     NOT NULL,
    status          CHAR(1)     NOT NULL,
    CONSTRAINT resdb_measure_cnvrtr_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_measure_cnvrtr_name_idx ON resdb_measure_converter (name);
CREATE UNIQUE INDEX resdb_measure_cnvrtr_from_to_idx ON resdb_measure_converter (from_measure_id, to_measure_id);

CREATE TABLE resdb_measure_system
(
    id           BIGINT      NOT NULL AUTO_INCREMENT,
    name         VARCHAR(30) NOT NULL,
    for_race_id  BIGINT      NULL,
    status       VARCHAR(1)  NOT NULL,
    created_by   VARCHAR(20) NOT NULL,
    last_updated TIMESTAMP   NULL,
    updated_by   VARCHAR(20) NULL,
    CONSTRAINT resdb_measure_system_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_measure_system_name_race_idx ON resdb_measure_system (name, for_race_id);

create table resdb_measure_type
(
    id                BIGINT      NOT NULL AUTO_INCREMENT,
    name              VARCHAR(30) NOT NULL,
    measure_system_id BIGINT      NULL,
    status            VARCHAR(1)  NOT NULL,
    created_by        VARCHAR(20) NOT NULL,
    last_updated      TIMESTAMP   NULL,
    updated_by        VARCHAR(20) NULL,
    CONSTRAINT resdb_measure_type_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_measure_type_name_idx ON resdb_measure_type (name);

CREATE TABLE resdb_menu
(
    id         BIGINT      NOT NULL AUTO_INCREMENT,
    name       VARCHAR(30) NOT NULL,
    owner_name VARCHAR(20) NOT NULL,
    owner_type VARCHAR(1)  NOT NULL,
    CONSTRAINT resdb_menu_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_menu_name_idx ON resdb_menu (owner_name);

CREATE TABLE resdb_menu_item
(
    id                     BIGINT      NOT NULL AUTO_INCREMENT,
    menu_id                BIGINT      NOT NULL,
    menu_level             INTEGER     NOT NULL,
    sequence_no            INTEGER     NOT NULL,
    internal_menuoption_id BIGINT      NOT NULL,
    parent_menuitem_id     BIGINT      NOT NULL,
    item_text              VARCHAR(30) NOT NULL,
    prompt                 varchar(50) NOT NULL,
    is_leaf_node           CHAR(1)     NOT NULL,
    has_separator_before   CHAR(1)     NOT NULL,
    has_separator_after    CHAR(1)     NOT NULL,
    permission             INTEGER     NOT NULL,
    CONSTRAINT resdb_menu_item_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_menu_item_idx1 ON resdb_menu_item (menu_id, parent_menuitem_id, item_text);
CREATE UNIQUE INDEX resdb_menu_item_idx2 ON resdb_menu_item (menu_id, menu_level, sequence_no);


CREATE TABLE resdb_menu_option
(
    id                BIGINT       NOT NULL AUTO_INCREMENT,
    name              VARCHAR(50)  NOT NULL,
    data_object       VARCHAR(50),
    default_menu_text VARCHAR(50)  NOT NULL,
    operation         VARCHAR(250) NOT NULL,
    CONSTRAINT resdb_menu_option_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_menu_item_name_idx ON resdb_menu_option (name);


--
-- TODO: OtherAddress.java links to EntityAddress and "address_text" so needs thinking about...
--
CREATE TABLE resdb_other_address
(
    id           BIGINT      NOT NULL AUTO_INCREMENT,
    name         VARCHAR(30) NOT NULL,
    status       VARCHAR(1)  NOT NULL,
    created_by   VARCHAR(20) NOT NULL,
    last_updated TIMESTAMP   NULL,
    updated_by   VARCHAR(20) NULL,
    CONSTRAINT resdb_other_address_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_other_address_name_idx ON resdb_other_address (name);


CREATE TABLE resdb_pattern_symbol
(
    id           BIGINT       NOT NULL AUTO_INCREMENT,
    name         VARCHAR(30)  NOT NULL,
    description  VARCHAR(250) NOT NULL,
    image_id     INTEGER      NOT NULL,
    race_id      INTEGER      NOT NULL,
    reference_id BIGINT       not null,
    CONSTRAINT resdb_pattern_symbol_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_pattern_symbol_name_idx ON resdb_pattern_symbol (name);


CREATE TABLE resdb_period
(
    id                 BIGINT      NOT NULL AUTO_INCREMENT,
    name               VARCHAR(30) NOT NULL,
    start_approximated CHAR(1)     NULL,
    period_start_year  INTEGER     NULL,
    period_start_month INTEGER     NULL,
    period_start_day   INTEGER     NULL,
    end_approximated   CHAR(1)     NULL,
    period_end_year    INTEGER     NULL,
    period_end_month   INTEGER     NULL,
    period_end_day     INTEGER     NULL,
    CONSTRAINT resdb_period_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_period_name_idx ON resdb_period (name);

CREATE TABLE resdb_period_alias
(
    id             BIGINT NOT NULL AUTO_INCREMENT,
    from_period_id BIGINT NOT NULL,
    to_period_id   BIGINT NOT NULL,
    CONSTRAINT resdb_period_alias_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_period_alias_idx ON resdb_period_alias (from_period_id, to_period_id);


CREATE TABLE resdb_permission
(
    id             BIGINT       NOT NULL AUTO_INCREMENT,
    name           VARCHAR(50)  NOT NULL,
    description    VARCHAR(100) NOT NULL,
    status         VARCHAR(1)   NOT NULL,
    operation_type VARCHAR(4)   NOT NULL,
    CONSTRAINT resdb_permission_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_permission_idx1 ON resdb_permission (id);
CREATE UNIQUE INDEX resdb_permission_idx2 ON resdb_permission (name);


CREATE TABLE resdb_person
(
    id                BIGINT      NOT NULL AUTO_INCREMENT,
    first_name        VARCHAR(30) NOT NULL,
    middle_name       VARCHAR(30) NULL,
    family_name       VARCHAR(50) NOT NULL,
    gender            VARCHAR(1)  NULL,
    birth_calendar_id BIGINT      NULL,
    birth_year        BIGINT      NULL,
    birth_month       INTEGER     NULL,
    birth_day         INTEGER     NULL,
    death_calendar_id BIGINT      NULL,
    death_year        INTEGER     NULL,
    death_month       INTEGER     NULL,
    death_day         INTEGER     NULL,
    prefix_title_id   BIGINT      NULL,
    suffix_title_id   BIGINT      NULL,
    birth_place_id    BIGINT      NULL,
    death_place_id    BIGINT      NULL,
    status            VARCHAR(1)  NOT NULL,
    created_by        VARCHAR(20) NOT NULL,
    last_updated      TIMESTAMP   NULL,
    updated_by        VARCHAR(20) NULL,
    CONSTRAINT resdb_person_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_person_name_idx ON resdb_person (family_name, middle_name, first_name);


CREATE TABLE resdb_person_alias
(
    id             BIGINT NOT NULL AUTO_INCREMENT,
    from_person_id BIGINT NOT NULL,
    to_person_id   BIGINT NOT NULL,
    CONSTRAINT resdb_person_alias_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_person_alias_idx ON resdb_person_alias (from_person_id, to_person_id);


CREATE TABLE resdb_person_definition
(
    person_id      BIGINT NOT NULL AUTO_INCREMENT,
    person_type_id BIGINT NOT NULL,
    CONSTRAINT resdb_person_definition_pk PRIMARY KEY (person_id)
);
CREATE UNIQUE INDEX resdb_person_definition_idx ON resdb_person_definition (person_id, person_type_id);

CREATE TABLE resdb_person_attribute
(
    id                       BIGINT NOT NULL AUTO_INCREMENT,
    person_id                BIGINT NOT NULL,
    person_attribute_type_id BIGINT NOT NULL,
    CONSTRAINT resdb_person_attribute_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_person_attribute_idx ON resdb_person_attribute (person_id, person_attribute_type_id);

CREATE TABLE resdb_person_role
(
    id              INTEGER     NOT NULL AUTO_INCREMENT,
    role_name       VARCHAR(30) NOT NULL,
    person_def_id   INTEGER     NULL,
    user_modifiable CHAR(1)     NOT NULL,
    CONSTRAINT resdb_person_role_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_person_role_name_idx ON resdb_person_role (role_name);

CREATE TABLE resdb_person_title
(
    id         BIGINT     NOT NULL AUTO_INCREMENT,
    person_id  BIGINT     NOT NULL,
    title_id   BIGINT     NOT NULL,
--     title_type VARCHAR(1) NOT NULL, -- P=prefix, S=suffix
    position   INTEGER    NOT NULL,
    CONSTRAINT resdb_person_title_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_person_title_idx ON resdb_person_title (person_id, title_id);

CREATE TABLE resdb_person_type
(
    id           BIGINT      NOT NULL AUTO_INCREMENT,
    name         VARCHAR(30) NOT NULL,
    status       VARCHAR(1)  NOT NULL,
    created_by   VARCHAR(20) NOT NULL,
    last_updated TIMESTAMP   NULL,
    updated_by   VARCHAR(20) NULL,
    CONSTRAINT resdb_person_type_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_person_type_name_idx ON resdb_person_type (name);


CREATE TABLE resdb_place
(
    id        BIGINT      NOT NULL AUTO_INCREMENT,
    name      VARCHAR(30) NOT NULL,
    latitude  VARCHAR(10) NULL,
    longitude VARCHAR(10) NULL,
    altitude  VARCHAR(10) NULL,
    river_id  BIGINT      NULL,
    status        VARCHAR(1)   NOT NULL,
    created_by    VARCHAR(20)  NOT NULL,
    last_updated  TIMESTAMP    NULL,
    updated_by    VARCHAR(20)  NULL,
    CONSTRAINT resdb_place_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX place_name_idx ON resdb_place (name);


CREATE TABLE resdb_place_alias
(
    id            BIGINT NOT NULL AUTO_INCREMENT,
    from_place_id BIGINT NOT NULL,
    to_place_id   BIGINT NOT NULL,
    CONSTRAINT resdb_place_alias_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_place_alias_idx ON resdb_place_alias (from_place_id, to_place_id);


CREATE TABLE resdb_postal_address
(
    id                BIGINT      NOT NULL AUTO_INCREMENT,
    entity_address_id BIGINT      NOT NULL,
    address_line1     VARCHAR(30) NOT NULL,
    address_line2     VARCHAR(30) NOT NULL,
    address_line3     VARCHAR(30) NOT NULL,
    address_line4     VARCHAR(30) NOT NULL,
    address_line5     VARCHAR(30) NOT NULL,
    CONSTRAINT resdb_postal_address_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_postal_address_idx ON resdb_postal_address (entity_address_id);


CREATE TABLE resdb_publication
(
    id                  BIGINT       NOT NULL AUTO_INCREMENT,
    title               VARCHAR(75)  NOT NULL,
    publication_type_id BIGINT       NOT NULL,
    author_person_id    BIGINT       NOT NULL,
    publisher_entity_id BIGINT       NOT NULL,
    isbn                VARCHAR(20)  NULL,
    published_year      INTEGER      NULL,
    published_month     INTEGER      NULL,
    published_day       INTEGER      NULL,
    is_primary          BIT          NULL,
    keywords            VARCHAR(250) NULL,
    assessment          VARCHAR(250) NULL,
    CONSTRAINT resdb_publication_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_publication_idx ON resdb_publication (title, publication_type_id, author_person_id, publisher_entity_id);


CREATE TABLE resdb_publication_role
(
    id             BIGINT      NOT NULL AUTO_INCREMENT,
    publication_id BIGINT      NOT NULL,
    person_id      BIGINT      NOT NULL,
    role_id        BIGINT      NOT NULL,
    chapter_title  VARCHAR(50) NULL,
    CONSTRAINT resdb_publication_role_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_publication_role_idx ON resdb_publication_role (publication_id, person_id, role_id, chapter_title);


CREATE TABLE resdb_publication_type
(
    id           BIGINT      NOT NULL AUTO_INCREMENT,
    name         VARCHAR(30) NOT NULL,
    status       VARCHAR(1)  NOT NULL,
    created_by   VARCHAR(20) NOT NULL,
    last_updated TIMESTAMP   NULL,
    updated_by   VARCHAR(20) NULL,
    CONSTRAINT resdb_publication_type_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_publication_type_name_idx ON resdb_publication_type (name);


CREATE TABLE resdb_race
(
    id           BIGINT      NOT NULL AUTO_INCREMENT,
    name         VARCHAR(30) NOT NULL,
    race_type_id BIGINT      NULL,
    CONSTRAINT resdb_race_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_race_name_idx ON resdb_race (name);


CREATE TABLE resdb_race_alias
(
    id           BIGINT NOT NULL AUTO_INCREMENT,
    from_race_id BIGINT NOT NULL,
    to_race_id   BIGINT NOT NULL,
    CONSTRAINT resdb_race_alias_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_race_alias_idx ON resdb_race_alias (from_race_id, to_race_id);

CREATE TABLE resdb_race_period
(
    id                 BIGINT  NOT NULL AUTO_INCREMENT,
    race_id            BIGINT  NOT NULL,
    period_id          BIGINT  NOT NULL,
    start_approximated CHAR(1) NOT NULL,
    period_start_year  INTEGER NOT NULL,
    period_start_month INTEGER NULL,
    period_start_day   INTEGER NULL,
    end_approximated   CHAR(1) NOT NULL,
    period_end_year    INTEGER NOT NULL,
    period_end_month   INTEGER NULL,
    period_end_day     INTEGER NULL,
    CONSTRAINT resdb_race_period_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_race_period_idx ON resdb_race_period (race_id, period_id);

CREATE TABLE resdb_race_period_alias
(
    id                  BIGINT NOT NULL AUTO_INCREMENT,
    from_race_period_id BIGINT NOT NULL,
    to_race_period_id   BIGINT NOT NULL,
    CONSTRAINT resdb_race_period_alias_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_race_period_alias_idx ON resdb_race_period_alias (from_race_period_id, to_race_period_id);


CREATE TABLE resdb_race_place
(
    id         BIGINT  NOT NULL AUTO_INCREMENT,
    race_id    BIGINT  NOT NULL,
    place_id   BIGINT  NOT NULL,
    from_year  INTEGER NULL,
    from_month INTEGER NULL,
    from_day   INTEGER NULL,
    to_year    INTEGER NULL,
    to_month   INTEGER NULL,
    to_day     INTEGER NULL,
    CONSTRAINT resdb_race_place_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_race_place_idx ON resdb_race_place (race_id, place_id);


CREATE TABLE resdb_race_type
(
    id           BIGINT      NOT NULL AUTO_INCREMENT,
    name         VARCHAR(30) NOT NULL,
    status       VARCHAR(1)  NOT NULL,
    created_by   VARCHAR(20) NOT NULL,
    last_updated TIMESTAMP   NULL,
    updated_by   VARCHAR(20) NULL,
    CONSTRAINT resdb_race_type_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_race_type_name_idx ON resdb_race_type (name);


CREATE TABLE resdb_reference
(
    id                   BIGINT       NOT NULL AUTO_INCREMENT,
    description          VARCHAR(250) NOT NULL,
    reference_type_id    BIGINT       NOT NULL,
    publication_id       BIGINT       NULL,
    page_number          INTEGER      NULL,
    relates_to_theory_id BIGINT       NULL,
    relates_to_tale_id   BIGINT       NULL,
    CONSTRAINT resdb_reference_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_reference_name_idx ON resdb_reference (reference_type_id, publication_id,
                                                                 relates_to_theory_id, relates_to_tale_id);


CREATE TABLE resdb_reference_type
(
    id           BIGINT      NOT NULL AUTO_INCREMENT,
    name         VARCHAR(30) NOT NULL,
    status       VARCHAR(1)  NOT NULL,
    created_by   VARCHAR(20) NOT NULL,
    last_updated TIMESTAMP   NULL,
    updated_by   VARCHAR(20) NULL,
    CONSTRAINT resdb_reference_type_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_reference_type_name_idx ON resdb_reference_type (name);


CREATE TABLE resdb_region
(
    id           BIGINT      NOT NULL AUTO_INCREMENT,
    name         VARCHAR(30) NOT NULL,
    status       VARCHAR(1)  NOT NULL,
    created_by   VARCHAR(20) NOT NULL,
    version_no   BIGINT      NOT NULL,
    last_updated TIMESTAMP   NULL,
    updated_by   VARCHAR(20) NULL,
    CONSTRAINT resdb_region_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_region_name_idx ON resdb_region (name);


CREATE TABLE resdb_religion
(
    id   BIGINT      NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    CONSTRAINT resdb_religion_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_religion_name_idx ON resdb_religion (name);


CREATE TABLE resdb_river
(
    id   BIGINT      NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    CONSTRAINT resdb_river_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_river_name_idx ON resdb_river (name);


CREATE TABLE resdb_river_alias
(
    id            BIGINT NOT NULL AUTO_INCREMENT,
    from_river_id BIGINT NOT NULL,
    to_river_id   BIGINT NOT NULL,
    CONSTRAINT resdb_river_alias_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_river_alias_idx ON resdb_river_alias (from_river_id, to_river_id);

CREATE TABLE resdb_system_parameters
(
    id            INTEGER      NOT NULL AUTO_INCREMENT,
    name          VARCHAR(30)  NOT NULL,
    description   VARCHAR(100) NULL,
    string_value  VARCHAR(30)  NULL,
    integer_value INTEGER      NULL,
    CONSTRAINT resdb_sysparams_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_sysparams_idx ON resdb_system_parameters (name);


CREATE TABLE resdb_tale
(
    id           BIGINT      NOT NULL AUTO_INCREMENT,
    name         VARCHAR(30) NOT NULL,
    tale_type_id BIGINT      NOT NULL,
    CONSTRAINT resdb_tale_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_tale_name_idx ON resdb_tale (name);


CREATE TABLE resdb_tale_type
(
    id           BIGINT      NOT NULL AUTO_INCREMENT,
    name         VARCHAR(30) NOT NULL,
    status       VARCHAR(1)  NOT NULL,
    created_by   VARCHAR(20) NOT NULL,
    last_updated TIMESTAMP   NULL,
    updated_by   VARCHAR(20) NULL,
    CONSTRAINT resdb_tale_type_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_tale_type_name_idx ON resdb_tale_type (name);


CREATE TABLE resdb_technology
(
    id                 BIGINT      NOT NULL AUTO_INCREMENT,
    name               VARCHAR(30) NOT NULL,
    technology_type_id BIGINT      NOT NULL,
    CONSTRAINT resdb_technology_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_technology_name_idx ON resdb_technology (name);


CREATE TABLE resdb_technology_type
(
    id               BIGINT      NOT NULL AUTO_INCREMENT,
    name             VARCHAR(30) NOT NULL,
    tech_type_grp_id BIGINT      NULL,
    CONSTRAINT resdb_technology_type_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX technology_type_name_idx ON resdb_technology_type (name);


CREATE TABLE resdb_technology_type_group
(
    id           BIGINT      NOT NULL AUTO_INCREMENT,
    name         VARCHAR(30) NOT NULL,
    status       VARCHAR(1)  NOT NULL,
    created_by   VARCHAR(20) NOT NULL,
    last_updated TIMESTAMP   NULL,
    updated_by   VARCHAR(20) NULL,
    CONSTRAINT resdb_technolgy_typ_grp_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_technlgy_typ_grp_nam_idx ON resdb_technology_type_group (name);


CREATE TABLE resdb_theory
(
    id               BIGINT       NOT NULL AUTO_INCREMENT,
    name             VARCHAR(30)  NOT NULL,
    description      VARCHAR(250) NOT NULL,
    author_person_id BIGINT       NOT NULL,
    CONSTRAINT resdb_theory_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_theory_name_idx ON resdb_theory (name);


create table resdb_title
(
    id           BIGINT       NOT NULL AUTO_INCREMENT,
    title        VARCHAR(30)  NOT NULL,
    description  VARCHAR(250) NULL,
    applies_to   VARCHAR(1)   NOT NULL, -- M=Male, F=Female
    title_type   VARCHAR(1)   NOT NULL, -- P=prefix, S=suffix
    status       VARCHAR(1)   NOT NULL,
    created_by   VARCHAR(20)  NOT NULL,
    last_updated TIMESTAMP    NULL,
    updated_by   VARCHAR(20)  NULL,
    CONSTRAINT resdb_title_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_title_title_mf_idx ON resdb_title (title, applies_to);
CREATE UNIQUE INDEX resdb_title_desc_mf_idx ON resdb_title (description, applies_to);
CREATE UNIQUE INDEX resdb_title_title_ps_idx ON resdb_title (title, title_type, applies_to);
CREATE UNIQUE INDEX resdb_title_desc_ps_idx ON resdb_title (description, title_type, applies_to);
CREATE INDEX resdb_title_applies_idx ON resdb_title (applies_to);
CREATE INDEX resdb_title_title_type_idx ON resdb_title (title_type);


CREATE TABLE resdb_user_account
(
    id                  BIGINT      NOT NULL AUTO_INCREMENT,
    logon_name          VARCHAR(20) NOT NULL,
    first_name          VARCHAR(30) NOT NULL,
    family_name         VARCHAR(50) NOT NULL,
    language_id         BIGINT      NOT NULL,
    is_template         BIT         NOT NULL,
    status              CHAR(1)     NOT NULL,
    last_logon          TIMESTAMP   NULL,
    invalid_logon_count INTEGER     null,
    pwd_last_updated    TIMESTAMP   NULL,
    CONSTRAINT resdb_user_account_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_user_account_idx ON resdb_user_account (logon_name);


CREATE TABLE resdb_user_group
(
    id           BIGINT      NOT NULL AUTO_INCREMENT,
    group_name   VARCHAR(20) NOT NULL,
    display_name VARCHAR(30) NOT NULL,
    status       CHAR(1)     NOT NULL,
    CONSTRAINT resdb_user_group_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_user_group_idx ON resdb_user_group (group_name);


CREATE TABLE resdb_user_group_membership
(
    id         BIGINT    NOT NULL AUTO_INCREMENT,
    valid_from TIMESTAMP NOT NULL,
    valid_to   TIMESTAMP NULL,
    account_id BIGINT    NOT NULL,
    group_id   BIGINT    NOT NULL,
    CONSTRAINT resdb_user_group_membership_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_user_group_membership_idx ON resdb_user_group_membership (account_id, group_id, valid_to);


CREATE TABLE resdb_user_password
(
    id         BIGINT      NOT NULL AUTO_INCREMENT,
    account_id BIGINT      NOT NULL,
    valid_from TIMESTAMP   NOT NULL,
    valid_to   TIMESTAMP   NULL,
    password   VARCHAR(30) NOT NULL,
    CONSTRAINT resdb_user_password_pk PRIMARY KEY (id)
);

CREATE TABLE resdb_user_group_permission
(
    id            INTEGER NOT NULL AUTO_INCREMENT,
    group_id      INTEGER NOT NULL,
    permission_id INTEGER NOT NULL,
    CONSTRAINT resdb_user_grp_perm_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_user_grp_perm_idx ON resdb_user_group_permission (group_id, permission_id);

