package com.apschulewitz.resdb.common.model.entity;

import org.springframework.util.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: Adrian
 * Date: 11/05/13
 * Time: 19:24
 * To change this template use File | Settings | File Templates.
 */
public enum VersionStatus {

    New("N"), Amend("A"), Cancel("C"),
    N("N"), A("A"), C("C"); // TODO remove these once JPA converters work correctly with Spring Boot (currently 1.5.2)

    private String code;

    private VersionStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static VersionStatus getInstance(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }

        switch (code) {
            case "N":
                return New;
            case "A":
                return Amend;
            case "C":
                return Cancel;
            default:
                return null;
        }
    }

    public static boolean isActive(VersionStatus status) {
        return status != null && (New.equals(status) || Amend.equals(status));
    }
}
