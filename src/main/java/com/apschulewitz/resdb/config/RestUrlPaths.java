package com.apschulewitz.resdb.config;

public final class RestUrlPaths {

    public static final String RESEARCH_DATABASE_BASE_URL = "/resdb/api";
    public static final String REFDATA_URL = RESEARCH_DATABASE_BASE_URL + "/refdata";
    public static final String RESEARCH_URL = RESEARCH_DATABASE_BASE_URL + "/research";

    // config

    public static final String CONFIG_URL = RESEARCH_DATABASE_BASE_URL + "/config";
    public static final String USER_ACCOUNT_CONTROLLER_BASE_URL = CONFIG_URL + "/users";
    public static final String USER_GROUP_CONTROLLER_BASE_URL = REFDATA_URL + "/usergroups";

    // reference data

    public static final String ADDRESS_TYPE_CONTROLLER_BASE_URL = REFDATA_URL + "/addresstypes";
    public static final String ARTEFACT_GROUP_CONTROLLER_BASE_URL = REFDATA_URL + "/artefactgroups";
    public static final String ARTEFACT_TYPE_CONTROLLER_BASE_URL = REFDATA_URL + "/artefacttypes";
    public static final String CALENDAR_TYPE_CONTROLLER_BASE_URL = REFDATA_URL + "/calendartypes";
    public static final String CLASSIFICATION_CONTROLLER_BASE_URL = REFDATA_URL + "/classifications";
    public static final String COUNTRY_CONTROLLER_BASE_URL = REFDATA_URL + "/countries";
    public static final String DEITY_TYPE_CONTROLLER_BASE_URL = REFDATA_URL + "/deitytypes";
    public static final String ENTITY_TYPE_CONTROLLER_BASE_URL = REFDATA_URL + "/entitytypes";
    public static final String EVENT_TYPE_GROUP_CONTROLLER_BASE_URL = REFDATA_URL + "/eventtypegroups";
    public static final String HIERARCHY_TYPE_CONTROLLER_BASE_URL = REFDATA_URL + "/hierarchytypes";
    public static final String IMAGE_TYPE_CONTROLLER_BASE_URL = REFDATA_URL + "/imagetypes";
    public static final String LANGUAGE_GROUP_CONTROLLER_BASE_URL = REFDATA_URL + "/languagegroups";
    public static final String MEASURE_TYPE_CONTROLLER_BASE_URL = REFDATA_URL + "/measuretypes";
    public static final String PERSON_CONTROLLER_BASE_URL = REFDATA_URL + "/persons";
    public static final String PERSON_TYPE_CONTROLLER_BASE_URL = REFDATA_URL + "/persontypes";
    public static final String PLACE_CONTROLLER_BASE_URL = REFDATA_URL + "/places";
    public static final String PUBLICATION_TYPE_CONTROLLER_BASE_URL = REFDATA_URL + "/publicationtypes";
    public static final String RACE_TYPE_CONTROLLER_BASE_URL = REFDATA_URL + "/racetypes";
    public static final String REFERENCE_TYPE_CONTROLLER_BASE_URL = REFDATA_URL + "/referencetypes";
    public static final String REGION_CONTROLLER_BASE_URL = REFDATA_URL + "/regions";
    public static final String TALE_TYPE_CONTROLLER_BASE_URL = REFDATA_URL + "/taletypes";
    public static final String TECHNOLOGY_TYPE_GROUP_CONTROLLER_BASE_URL = REFDATA_URL + "/technologytypegroups";

    // research data

    public static final String TITLE_CONTROLLER_BASE_URL = RESEARCH_URL + "/titles";

    // authentication and authorization url's
    private static final String AUTH_CONTROLLER_BASE_URL = RESEARCH_DATABASE_BASE_URL + "/auth";
    public static final String LOGIN_PAGE_URL = AUTH_CONTROLLER_BASE_URL + "/login";
    public static final String LOGOUT_PAGE_URL = AUTH_CONTROLLER_BASE_URL + "/logout";
    public static final String AUTHENTICATE_URL = AUTH_CONTROLLER_BASE_URL + "/authenticate";

    public static final String TEST_PAGE_URL = AUTH_CONTROLLER_BASE_URL + "/hello";
    public static final String TEST_PAGE_WITH_DATA_URL = AUTH_CONTROLLER_BASE_URL + "/helloWithData";

}
