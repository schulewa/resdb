/*use resdb_dev;*/

CREATE TABLE resdb_address_type_aud
(
	id				        BIGINT      NOT NULL,
  rev               BIGINT      NOT NULL,
  revtype           SMALLINT    NULL,
	name			        VARCHAR(30)	NOT NULL
);

CREATE TABLE resdb_artefact_aud
(
	id		              BIGINT        NOT NULL,
  rev                 BIGINT        NOT NULL,
  revtype             SMALLINT      NULL,
	name	              VARCHAR(30)		NOT NULL,
  description         VARCHAR(250)  NULL,
  date_found_year     INTEGER       NULL,
  date_found_month    INTEGER       NULL,
  date_found_day      INTEGER       NULL,
  artefact_type_id    BIGINT     	  NOT NULL,
  found_by_person_id  BIGINT        NULL,
  artefact_group_id   BIGINT        NULL,
  owner_identifier    VARCHAR(10)   NULL,
  technology_id       BIGINT        NULL,
  composition_id      BIGINT        NULL
);


CREATE TABLE resdb_artefact_attribute_aud
(
	id								          BIGINT      NOT NULL,
  rev                         BIGINT      NOT NULL,
  revtype                     SMALLINT    NULL,
  artefact_id                 BIGINT 	    NOT NULL,
  artefact_attribute_type_id  BIGINT      NOT NULL
);


CREATE TABLE resdb_artefact_attribute_type_aud
(
	id                            BIGINT      NOT NULL,
  rev                           BIGINT      NOT NULL,
  revtype                       SMALLINT    NULL,
  name                          VARCHAR(20) NOT NULL,
  data_type                     VARCHAR(20) NOT NULL,
  is_mandatory                  BIT         NOT NULL,
  data_class_association_id     BIGINT      NULL
);


CREATE TABLE resdb_artefact_group_aud
(
	id  	        	    BIGINT      NOT NULL,
  rev                 BIGINT      NOT NULL,
  revtype             SMALLINT    NULL,
  name    	    	    VARCHAR(20)	NOT NULL
);


CREATE TABLE resdb_artefact_image_aud
(
	id							            BIGINT      NOT NULL,
  rev                         BIGINT      NOT NULL,
  revtype                     SMALLINT    NULL,
	artefact_id                 BIGINT		  NOT NULL,
  image_collection_header_id  BIGINT		  NOT NULL
);


CREATE TABLE resdb_artefact_img_pttrn_smbl_aud
(
	id						        BIGINT      NOT NULL,
  rev                   BIGINT      NOT NULL,
  revtype               SMALLINT    NULL,
	artefact_image_id  		BIGINT		  NOT NULL,
  pattern_symbol_id  		BIGINT		  NOT NULL
);


CREATE TABLE resdb_artefact_place_aud
(
	id					        BIGINT      NOT NULL,
  rev                 BIGINT      NOT NULL,
  revtype             SMALLINT    NULL,
	artefact_id         BIGINT		  NOT NULL,
  place_id            BIGINT		  NOT NULL,
  held_at_entity_id   BIGINT		  NOT NULL
);


CREATE TABLE resdb_artefact_rstrtn_work_aud
(
	id                    BIGINT          NOT NULL,
  rev                   BIGINT          NOT NULL,
  revtype               SMALLINT        NULL,
  description           VARCHAR(250)    NULL,
  artefact_id           INTEGER         NOT NULL,
  date_started_year     INTEGER         NULL,
  date_started_month    INTEGER         NULL,
  date_started_day      INTEGER         NULL,
  date_finished_year    INTEGER         NULL,
  date_finished_month   INTEGER         NULL
);


CREATE TABLE resdb_artefact_type_aud
(
	id				          BIGINT        NOT NULL,
  rev                 BIGINT        NOT NULL,
  revtype             SMALLINT      NULL,
  name    	          VARCHAR(20)		NOT NULL
);


CREATE TABLE resdb_bibliography_aud
(
	id          	      BIGINT        NOT NULL,
  rev                 BIGINT        NOT NULL,
  revtype             SMALLINT      NULL,
  name        	      VARCHAR(30) 	NOT NULL
);


CREATE TABLE resdb_bibliography_publication_aud
(
	id						      BIGINT      NOT NULL,
  rev                 BIGINT      NOT NULL,
  revtype             SMALLINT    NULL,
	bibliography_id			BIGINT 	    NOT NULL,
  publication_id			BIGINT      NOT NULL
);


CREATE TABLE resdb_calendar_aud
(
	id                  BIGINT        NOT NULL,
  rev                 BIGINT        NOT NULL,
  revtype             SMALLINT      NULL,
  name                VARCHAR(30)		NOT NULL,
  calendar_type_id    BIGINT			  NOT NULL
);


CREATE TABLE resdb_calendar_race_aud
(
	id				          BIGINT        NOT NULL,
  rev                 BIGINT        NOT NULL,
  revtype             SMALLINT      NULL,
	calendar_id         BIGINT 	      NOT NULL,
  race_id             INTEGER       NOT NULL
);


CREATE TABLE resdb_calendar_type_aud
(
	id                BIGINT        NOT NULL,
  rev               BIGINT        NOT NULL,
  revtype           SMALLINT      NULL,
  name              VARCHAR(30) 	NOT NULL
);


CREATE TABLE resdb_country_aud
(
	id                BIGINT        NOT NULL,
  rev               BIGINT        NOT NULL,
  revtype           SMALLINT      NULL,
  name              VARCHAR(30) 	NOT NULL
);


CREATE TABLE resdb_country_region_aud
(
	id                BIGINT        NOT NULL,
  rev               BIGINT        NOT NULL,
  revtype           SMALLINT      NULL,
  name              VARCHAR(30) 	NOT NULL
);


CREATE TABLE resdb_data_class_aud
(
  id                          BIGINT        NOT NULL,
  rev                         BIGINT        NOT NULL,
  revtype                     SMALLINT      NULL,
  data_class_key				      VARCHAR(30)		NOT NULL,
  fully_qualified_class_name  VARCHAR(250)  NOT NULL
);


CREATE TABLE resdb_data_class_method_aud
(
	id				          BIGINT        NOT NULL,
  rev                 BIGINT        NOT NULL,
  revtype             SMALLINT      NULL,
  data_class_key      VARCHAR(30)		NOT NULL,
  method_name         VARCHAR(250)	NOT NULL
);


CREATE TABLE resdb_data_class_method_argmnt_aud
(
	id						        BIGINT        NOT NULL,
  rev                   BIGINT        NOT NULL,
  revtype               SMALLINT      NULL,
  data_class_key        VARCHAR(30)   NOT NULL,
  method_name           varchar(250)  NOT NULL,
  argument_sequence_no  INTEGER       NOT NULL,
  argument_data_type    VARCHAR(250)  NOT NULL
);


CREATE TABLE resdb_deity_aud
(
	id              		BIGINT        NOT NULL ,
  rev                 BIGINT        NOT NULL,
  revtype             SMALLINT      NULL,
  name            		VARCHAR(30)		NOT NULL
);


CREATE TABLE resdb_deity_alias_aud
(
  id              		BIGINT      NOT NULL,
  rev                 BIGINT      NOT NULL,
  revtype             SMALLINT    NULL,
	from_deity_id       BIGINT 	    NOT NULL,
  to_alias_id         BIGINT      NOT NULL
);


CREATE TABLE resdb_deity_religion_aud
(
  id              		BIGINT      NOT NULL,
  rev                 BIGINT      NOT NULL,
  revtype             SMALLINT    NULL,
	deity_id            BIGINT		  NOT NULL,
  religion_alias_id   BIGINT		  NOT NULL
);


CREATE TABLE resdb_deity_type_aud
(
	id						    BIGINT        NOT NULL,
  rev               BIGINT        NOT NULL,
  revtype           SMALLINT      NULL,
  name					    VARCHAR(30)		NOT NULL
);

CREATE TABLE resdb_dropdown_class_aud (
  id                  INTEGER       NOT NULL AUTO_INCREMENT,
  name                VARCHAR(100)  NOT NULL,
  CONSTRAINT resdb_dropdown_class_aud_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_dropdown_class_aud_idx ON resdb_dropdown_class_aud (name);

CREATE TABLE resdb_dropdown_domain_aud (
  id                  INTEGER       NOT NULL AUTO_INCREMENT,
  short_code          VARCHAR(10)   NOT NULL,
  description         VARCHAR(100)  NOT NULL,
  dropdown_class_id   INTEGER       NOT NULL,
  CONSTRAINT resdb_dropdown_domain_aud_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_dropdown_domain_aud_idx ON resdb_dropdown_domain_aud (short_code);

CREATE TABLE resdb_entity_aud
(
	id                BIGINT        NOT NULL,
  rev               BIGINT        NOT NULL,
  revtype           SMALLINT      NULL,
  name              VARCHAR(30)		NOT NULL,
  entity_type_id    INTEGER			  NOT NULL
);


CREATE TABLE resdb_entity_type_aud
(
	id              	BIGINT        NOT NULL,
  rev               BIGINT        NOT NULL,
  revtype           SMALLINT      NULL,
  name            	VARCHAR(30)		NOT NULL
);


CREATE TABLE resdb_entity_address_aud
(
	id                  BIGINT      NOT NULL,
  rev                 BIGINT      NOT NULL,
  revtype             SMALLINT    NULL,
  entity_id           BIGINT     	NOT NULL,
  address_type_id     BIGINT     	NOT NULL,
  address_id          BIGINT     	NOT NULL
);


CREATE TABLE resdb_event_aud
(
	id                  BIGINT        NOT NULL,
  rev                 BIGINT        NOT NULL,
  revtype             SMALLINT      NULL,
  name                VARCHAR(30)		NOT NULL,
  event_type_id       BIGINT     	  NOT NULL,
  description         VARCHAR(250)	NULL,
  event_date_year     INTEGER       NULL,
  event_date_month    INTEGER       NULL,
  event_date_day      INTEGER       NULL,
  place_id            INTEGER       NULL
);


CREATE TABLE resdb_event_person_aud
(
  id					        BIGINT        NOT NULL,
  rev                 BIGINT        NOT NULL,
  revtype             SMALLINT      NULL,
	event_id        	  BIGINT 		    NOT NULL,
  person_id       	  BIGINT     	  NOT NULL,
  involvement     	  VARCHAR(250)  NULL
);


CREATE TABLE resdb_event_attribute_aud
(
  id					              BIGINT      NOT NULL,
  rev                       BIGINT      NOT NULL,
  revtype                   SMALLINT    NULL,
	event_id                  BIGINT 	    NOT NULL,
  event_attribute_type_id   BIGINT      NOT NULL
);


CREATE TABLE resdb_event_attribute_type_aud
(
  id					                BIGINT        NOT NULL,
  rev                         BIGINT        NOT NULL,
  revtype                     SMALLINT      NULL,
	event_id                    BIGINT     	  NOT NULL,
  name                        VARCHAR(20)   NOT NULL,
  is_mandatory                BIT           NOT NULL,
  data_class_association_id   BIGINT        NOT NULL
);


CREATE TABLE resdb_event_type_aud
(
	id                  	BIGINT        NOT NULL,
  rev                   BIGINT        NOT NULL,
  revtype               SMALLINT      NULL,
  name                	VARCHAR(30)		NOT NULL,
  event_type_group_id 	BIGINT			  NOT NULL
);


CREATE TABLE resdb_event_type_group_aud
(
	id					          BIGINT        NOT NULL,
  rev                   BIGINT        NOT NULL,
  revtype               SMALLINT      NULL,
  name        		      VARCHAR(30)		NOT NULL
);


CREATE TABLE resdb_hierarchy_aud
(
	id                  BIGINT        NOT NULL,
  rev                 BIGINT        NOT NULL,
  revtype             SMALLINT      NULL,
  name                VARCHAR(30)		NOT NULL,
  hierarchy_type_id   BIGINT			  NOT NULL,
  version_no 			    INTEGER			  NOT NULL
);


CREATE TABLE resdb_hierarchy_node_aud
(
	id                          BIGINT        NOT NULL,
  rev                         BIGINT        NOT NULL,
  revtype                     SMALLINT      NULL,
  name                        VARCHAR(30)		NOT NULL,
  parent_hierarchy_id         BIGINT			  NOT NULL,
  first_child_hierarchy_id    BIGINT        NULL,
  last_child_hierarchy_id     BIGINT        NULL,
  leaf_node_ind               BIT         	NOT NULL
);


CREATE TABLE resdb_hierarchy_type_aud
(
	id							  BIGINT        NOT NULL,
  rev               BIGINT        NOT NULL,
  revtype           SMALLINT      NULL,
  name            	VARCHAR(30)		NOT NULL
);


CREATE TABLE resdb_image_aud
(
	id              	BIGINT        NOT NULL,
  rev               BIGINT        NOT NULL,
  revtype           SMALLINT      NULL,
  file_path       	VARCHAR(30)		NOT NULL,
  file_name       	VARCHAR(30)		NOT NULL,
  image_type_id   	BIGINT     	  NOT NULL
);


CREATE TABLE resdb_image_collection_aud
(
	id                  BIGINT        NOT NULL,
  rev                 BIGINT        NOT NULL,
  revtype             SMALLINT      NULL,
  name                VARCHAR(30)		NOT NULL,
  number_of_rows      INTEGER       NULL,
  number_of_columns   INTEGER       NULL
);


create table resdb_image_collection_seq_aud
(
  id                    BIGINT        NOT NULL,
  rev                   BIGINT        NOT NULL,
  revtype               SMALLINT      NULL,
	image_collection_id		BIGINT			  NOT NULL,
  row_number          	INTEGER       NOT NULL,
  column_number       	INTEGER       NOT NULL,
  artefact_image_id   	BIGINT        NOT NULL
);

CREATE TABLE resdb_image_type_aud
(
  id				            INTEGER			  NOT NULL AUTO_INCREMENT,
  name    	            VARCHAR(20)	  NOT NULL,
  CONSTRAINT resdb_image_type_aud_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_image_typ_nam_aud_idx on resdb_image_type_aud (name);

create table resdb_language_aud
(
	id						          BIGINT        NOT NULL,
  rev                     BIGINT        NOT NULL,
  revtype                 SMALLINT      NULL,
	iso_6391_code           VARCHAR(5)    NOT NULL,
	iso_6392_code_alpha_3b  VARCHAR(3)    NOT NULL,
	iso_6392_code_alpha_3t  VARCHAR(3)    NULL,
	iso_6392_code_alpha_2   VARCHAR(2)    NULL,
  name                	  VARCHAR(30)		NOT NULL,
  language_group_id   	  BIGINT     	  NOT NULL
);


create table resdb_language_group_aud
(
	id					      BIGINT        NOT NULL,
  rev               BIGINT        NOT NULL,
  revtype           SMALLINT      NULL,
  name        		  VARCHAR(30)  	NOT NULL
);


CREATE TABLE resdb_language_pattern_symbol_aud
(
  id					        BIGINT      NOT NULL,
  rev                 BIGINT      NOT NULL,
  revtype             SMALLINT    NULL,
	language_id         BIGINT   	  NOT NULL,
  pattern_symbol_id   BIGINT      NOT NULL
);


create table resdb_library_location_aud
(
  id					        BIGINT      NOT NULL,
  rev                 BIGINT      NOT NULL,
  revtype             SMALLINT    NULL,
	publication_id      BIGINT   	  NOT NULL,
  entity_id           BIGINT      NOT NULL
);


create table resdb_measure_aud
(
	id                    BIGINT        NOT NULL,
  rev                   BIGINT        NOT NULL,
  revtype               SMALLINT      NULL,
  name                  VARCHAR(30)		NOT NULL,
  from_measure_type_id  BIGINT     	  NOT NULL,
  from_quantity         BIGINT       	NOT NULL,
  to_measure_type_id    BIGINT     	  NOT NULL,
  to_quantity           FLOAT       	NOT NULL
);


CREATE TABLE resdb_material_aud
(
	id                    BIGINT        NOT NULL,
  rev                   BIGINT        NOT NULL,
  revtype               SMALLINT      NULL,
  name                  VARCHAR(30)		NOT NULL,
  from_measure_type_id  BIGINT			  NOT NULL,
  from_quantity         FLOAT       	NOT NULL,
  to_measure_type_id    BIGINT     	  NOT NULL,
  to_quantity           FLOAT       	NOT NULL
);


create table resdb_measure_type_aud
(
	id						    BIGINT        NOT NULL,
  rev               BIGINT        NOT NULL,
  revtype           SMALLINT      NULL,
  name        			VARCHAR(30)		NOT NULL
);


# CREATE TABLE resdb_menu
# (
# 	id              		BIGINT        NOT NULL AUTO_INCREMENT,
#   rev                 BIGINT        NOT NULL,
#   revtype             SMALLINT      NULL,
#   name            		VARCHAR(30)		NOT NULL,
#   owner_user_id   		BIGINT     	  NOT NULL,
#   owner_type      		CHAR(1)     	NOT NULL,
# 	CONSTRAINT resdb_menu_pk PRIMARY KEY (id)
# );
# CREATE UNIQUE INDEX resdb_menu_name_idx ON resdb_menu (owner_user_id);
#
#
# CREATE TABLE resdb_menu_item
# (
#   id                        BIGINT        NOT NULL AUTO_INCREMENT,
#   rev                       BIGINT        NOT NULL,
#   revtype                   SMALLINT      NULL,
# 	menu_id                   BIGINT        NOT NULL,
#   level                     INTEGER     	NOT NULL,
#   sequence_no               INTEGER     	NOT NULL,
#   internal_menu_id          BIGINT     	  NOT NULL,
#   parent_menu_id            BIGINT     	  NOT NULL,
#   item_text                 VARCHAR(20)		NOT NULL,
#   prompt                    varchar(50)		NOT NULL,
#   is_leaf_node              BIT         	NOT NULL,
#   has_separator_before      bit         	NOT NULL,
#   has_separator_after       BIT         	NOT NULL,
#   permission                INTEGER     	NOT NULL
# );


# CREATE TABLE resdb_menu_option
# (
# 	id						    BIGINT        NOT NULL AUTO_INCREMENT,
#   rev               BIGINT        NOT NULL,
#   revtype           SMALLINT      NULL,
#   name    				  VARCHAR(30)   NOT NULL
# );


CREATE TABLE resdb_other_address_aud
(
	id						    BIGINT        NOT NULL,
  rev               BIGINT        NOT NULL,
  revtype           SMALLINT      NULL,
  name					    VARCHAR(30)   NOT NULL
);


CREATE TABLE resdb_pattern_symbol_aud
(
	id              		BIGINT        NOT NULL,
  rev                 BIGINT        NOT NULL,
  revtype             SMALLINT      NULL,
  name            		VARCHAR(30)   NOT NULL,
  description     		VARCHAR(250)  NOT NULL,
  image_id        		INTEGER       NOT NULL,
  race_id         		INTEGER       NOT NULL,
  reference_id    		BIGINT        not null
);


CREATE TABLE resdb_period_aud
(
	id      				      BIGINT        NOT NULL,
  name    				      VARCHAR(30)   NOT NULL,
  period_start_year   	INTEGER     	NOT NULL,
  period_start_month  	INTEGER       NULL,
  period_start_day    	INTEGER       NULL,
  period_end_year     	INTEGER     	NOT NULL,
  period_end_month    	INTEGER       NULL,
  period_end_day      	INTEGER       NULL
);

# CREATE TABLE resdb_permission (
#   id                    BIGINT        NOT NULL,
#   name                  VARCHAR(30)   NOT NULL,
#   description           VARCHAR(100)  NOT NULL,
#   status                VARCHAR(1)    NOT NULL,
#   operation_type        VARCHAR(4)    NOT NULL
# );


CREATE TABLE resdb_person_aud
(
	id              		BIGINT        NOT NULL,
  rev                 BIGINT        NOT NULL,
  revtype             SMALLINT      NULL,
  first_name      		VARCHAR(30)   NOT NULL,
  middle_name     		VARCHAR(30)   NULL,
  family_name     		VARCHAR(50)   NOT NULL,
  birth_calendar_id		BIGINT			  NULL,
  birth_year_id      	BIGINT        NULL,
  birth_month     		INTEGER       NULL,
  birth_day       		INTEGER       NULL,
  death_calendar_id		BIGINT			  NULL,
  death_year      		INTEGER       NULL,
  death_month     		INTEGER       NULL,
  death_day       		INTEGER       NULL,
  prefix_title_id 		BIGINT        NULL,
  suffix_title_id 		BIGINT        NULL,
  born_place_id   		BIGINT        NULL,
  death_place_id  		BIGINT        NULL
);


CREATE TABLE resdb_person_alias_aud
(
	id					        BIGINT      NOT NULL,
  rev                 BIGINT      NOT NULL,
  revtype             SMALLINT    NULL,
	from_person_id      BIGINT      NOT NULL,
  to_person_id        BIGINT      NOT NULL
);


CREATE TABLE resdb_person_definition_aud
(
  id                  BIGINT      NOT NULL,
  rev                 BIGINT      NOT NULL,
  revtype             SMALLINT    NULL,
	person_id           BIGINT      NOT NULL,
  person_type_id      BIGINT      NOT NULL
);

CREATE TABLE resdb_person_role_aud
(
  id      				    INTEGER       NOT NULL AUTO_INCREMENT,
  role_name    			  VARCHAR(30)   NOT NULL,
  person_def_id       INTEGER       NULL,
  user_modifiable     CHAR(1)       NOT NULL,
  CONSTRAINT resdb_person_role_aud_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_person_role_name_aud_idx ON resdb_person_role_aud (role_name);

CREATE TABLE resdb_person_type_aud
(
	id      				  BIGINT        NOT NULL,
  rev               BIGINT        NOT NULL,
  revtype           SMALLINT      NULL,
  name    				  VARCHAR(30)   NOT NULL
);


CREATE TABLE resdb_place_aud
(
	id          			BIGINT        NOT NULL,
  rev               BIGINT        NOT NULL,
  revtype           SMALLINT      NULL,
  name        			VARCHAR(30)		NOT NULL,
  latitude    			VARCHAR(10)		NOT NULL,
  longitude   			VARCHAR(10)		NOT NULL,
  altitude    			VARCHAR(10)		NOT NULL,
  river_id    			BIGINT        NULL
);


CREATE TABLE resdb_place_alias_aud
(
  id          			  BIGINT      NOT NULL,
  rev                 BIGINT      NOT NULL,
  revtype             SMALLINT    NULL,
	from_place_id       BIGINT      NOT NULL,
  to_place_id         BIGINT      NOT NULL
);


CREATE TABLE resdb_postal_address_aud
(
  id          			  BIGINT        NOT NULL,
  rev                 BIGINT        NOT NULL,
  revtype             SMALLINT      NULL,
	entity_address_id   BIGINT			  NOT NULL,
  address_line1       VARCHAR(30)		NOT NULL,
  address_line2       VARCHAR(30)		NOT NULL,
  address_line3       VARCHAR(30)		NOT NULL,
  address_line4       VARCHAR(30)		NOT NULL,
  address_line5       VARCHAR(30)		NOT NULL
);


CREATE TABLE resdb_publication_aud
(
	id                  	BIGINT        NOT NULL,
  rev                   BIGINT        NOT NULL,
  revtype               SMALLINT      NULL,
  title               	VARCHAR(75)   NOT NULL,
  publication_type_id 	BIGINT        NOT NULL,
  author_person_id    	BIGINT        NOT NULL,
  publisher_entity_id 	BIGINT        NOT NULL,
  isbn                	VARCHAR(20)   NULL,
  published_year      	INTEGER       NULL,
  published_month     	INTEGER       NULL,
  published_day       	INTEGER       NULL,
  is_primary          	BIT           NULL,
  keywords            	VARCHAR(250)  NULL,
  assessment          	VARCHAR(250)  NULL
);


CREATE TABLE resdb_publication_role_aud
(
  id                  	BIGINT        NOT NULL,
  rev                   BIGINT        NOT NULL,
  revtype               SMALLINT      NULL,
  publication_id      	BIGINT     	  NOT NULL,
  person_id           	BIGINT     	  NOT NULL,
  role_id             	BIGINT     	  NOT NULL,
  chapter_title       	VARCHAR(50)   NULL
);


CREATE TABLE resdb_publication_type_aud
(
	id      				  BIGINT        NOT NULL,
  rev               BIGINT        NOT NULL,
  revtype           SMALLINT      NULL,
  name    				  VARCHAR(30)   NOT NULL
);


CREATE TABLE resdb_race_aud
(
	id              	BIGINT        NOT NULL,
  rev               BIGINT        NOT NULL,
  revtype           SMALLINT      NULL,
  name            	VARCHAR(30)   NOT NULL,
  race_type_id    	BIGINT        NOT NULL
);


CREATE TABLE resdb_race_alias_aud
(
  id              	BIGINT      NOT NULL,
  rev               BIGINT      NOT NULL,
  revtype           SMALLINT    NULL,
	from_race_id      BIGINT      NOT NULL,
  to_race_id        BIGINT      NOT NULL
);


CREATE TABLE resdb_race_place_aud
(
  id              	BIGINT      NOT NULL,
  rev               BIGINT      NOT NULL,
  revtype           SMALLINT    NULL,
	race_id     			BIGINT     	NOT NULL,
  place_id    			BIGINT     	NOT NULL,
  from_year   			INTEGER     NULL,
  from_month  			INTEGER     NULL,
  from_day    			INTEGER     NULL,
  to_year     			INTEGER     NULL,
  to_month    			INTEGER     NULL,
  to_day      			INTEGER     NULL
);


CREATE TABLE resdb_race_type_aud
(
	id      				  BIGINT        NOT NULL,
  rev               BIGINT        NOT NULL,
  revtype           SMALLINT      NULL,
  name    				  VARCHAR(30)   NOT NULL
);


CREATE TABLE resdb_reference_aud
(
	id                      BIGINT        NOT NULL,
  rev                     BIGINT        NOT NULL,
  revtype                 SMALLINT      NULL,
  description             VARCHAR(250)  NOT NULL,
  reference_type_id       BIGINT        NOT NULL,
  publication_id          BIGINT        NULL,
  page_number             INTEGER       NULL,
  relates_to_theory_id    BIGINT        NULL,
  relates_to_tale_id      BIGINT        NULL
);


CREATE TABLE resdb_reference_type_aud
(
	id      				  BIGINT        NOT NULL,
  rev               BIGINT        NOT NULL,
  revtype           SMALLINT      NULL,
  name    				  VARCHAR(30)   NOT NULL
);


CREATE TABLE resdb_region_aud
(
	id      				  BIGINT        NOT NULL,
  rev               BIGINT        NOT NULL,
  revtype           SMALLINT      NULL,
  name    				  VARCHAR(30)   NOT NULL
);


CREATE TABLE resdb_religion_aud
(
	id      				  BIGINT          NOT NULL,
  rev               BIGINT          NOT NULL,
  revtype           SMALLINT        NULL,
  name    				  VARCHAR(30)     NOT NULL
);


CREATE TABLE resdb_river_aud
(
	id      				  BIGINT        NOT NULL,
  rev               BIGINT        NOT NULL,
  revtype           SMALLINT      NULL,
  name    				  VARCHAR(30)   NOT NULL
);


CREATE TABLE resdb_river_alias_aud
(
  id      				  BIGINT      NOT NULL,
  rev               BIGINT      NOT NULL,
  revtype           SMALLINT    NULL,
	from_river_id     BIGINT      NOT NULL,
  to_river_id       BIGINT      NOT NULL
);

CREATE TABLE resdb_system_parameters_aud
(
  id      				        INTEGER        	NOT NULL AUTO_INCREMENT,
  name    				        VARCHAR(30)     NOT NULL,
  description             VARCHAR(100)    NULL,
  string_value            VARCHAR(30)     NULL,
  integer_value           INTEGER         NULL,
  CONSTRAINT resdb_sysparams_aud_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX resdb_sysparams_aud_idx ON resdb_system_parameters_aud (name);

CREATE TABLE resdb_tale_aud
(
	id              	BIGINT        NOT NULL,
  rev               BIGINT        NOT NULL,
  revtype           SMALLINT      NULL,
  name            	VARCHAR(30)   NOT NULL,
  tale_type_id    	BIGINT        NOT NULL
);


CREATE TABLE resdb_tale_type_aud
(
	id      				  BIGINT        NOT NULL,
  rev               BIGINT        NOT NULL,
  revtype           SMALLINT      NULL,
  name    				  VARCHAR(30)   NOT NULL
);


CREATE TABLE resdb_technology_aud
(
	id                  BIGINT        NOT NULL,
  rev                 BIGINT        NOT NULL,
  revtype             SMALLINT      NULL,
  name                VARCHAR(30)		NOT NULL,
  technology_type_id  BIGINT     	  NOT NULL
);


CREATE TABLE resdb_technology_type_aud
(
	id      				BIGINT        NOT NULL,
  rev             BIGINT        NOT NULL,
  revtype         SMALLINT      NULL,
  name    				VARCHAR(30)   NOT NULL
);


CREATE TABLE resdb_technology_type_group_aud
(
	id      				  BIGINT        NOT NULL,
  rev               BIGINT        NOT NULL,
  revtype           SMALLINT      NULL,
  name    				  VARCHAR(30)   NOT NULL
);


CREATE TABLE resdb_theory_aud
(
	id                  BIGINT        NOT NULL,
  rev                 BIGINT        NOT NULL,
  revtype             SMALLINT      NULL,
  name                VARCHAR(30)   NOT NULL,
  description         VARCHAR(250)  NOT NULL,
  author_person_id    BIGINT        NOT NULL
);


create table resdb_title_aud
(
	id                  BIGINT        NOT NULL,
  rev                 BIGINT        NOT NULL,
  revtype             SMALLINT      NULL,
  name                VARCHAR(30)   NOT NULL,
  description         VARCHAR(250)  NOT NULL,
  author_person_id    BIGINT        NOT NULL
);


CREATE TABLE resdb_user_account_aud
(
  id                    BIGINT        NOT NULL,
  rev                   BIGINT        NOT NULL,
  revtype               SMALLINT      NULL,
  logon_name          	VARCHAR(20)   NOT NULL,
  first_name          	VARCHAR(30)   NOT NULL,
  family_name         	VARCHAR(50)   NOT NULL,
  language_id         	BIGINT        NOT NULL,
  status              	CHAR(1)       NOT NULL,
  last_logon          	TIMESTAMP     NULL,
  invalid_logon_count 	INTEGER       null,
  pwd_last_updated      TIMESTAMP     NULL
);


CREATE TABLE resdb_user_group_aud
(
  id                  BIGINT        NOT NULL,
  rev                 BIGINT        NOT NULL,
  revtype             SMALLINT      NULL,
  group_name      		VARCHAR(20)   NOT NULL,
  display_name    		VARCHAR(30)   NOT NULL
);


CREATE TABLE resdb_user_group_membership_aud
(
  id                BIGINT        NOT NULL,
  rev               BIGINT        NOT NULL,
  revtype           SMALLINT      NULL,
  valid_from        TIMESTAMP     NOT NULL,
  valid_to          TIMESTAMP     NULL,
  logon_name        VARCHAR(20)   NOT NULL,
  group_name        VARCHAR(20)   NOT NULL
);


CREATE TABLE resdb_user_password_aud
(
  id                BIGINT        NOT NULL,
  rev               BIGINT        NOT NULL,
  revtype           SMALLINT      NULL,
  logon_name        VARCHAR(20)   NOT NULL,
  valid_from        TIMESTAMP     NOT NULL,
  valid_to          TIMESTAMP     NULL,
  password          VARCHAR(30)   NOT NULL
);

