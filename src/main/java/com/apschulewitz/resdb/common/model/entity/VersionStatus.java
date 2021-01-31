package com.apschulewitz.resdb.common.model.entity;

import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    VersionStatus(@NotBlank String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static VersionStatus getInstance(@NotBlank String codeOrName) {
      if (StringUtils.isEmpty(codeOrName))
        return New;

      Optional<VersionStatus> versionStatus = Arrays.stream(values())
        .filter(e -> e.name().length() > 1)
        .filter(e -> e.name().equals(codeOrName))
        .findFirst();

      if (versionStatus.isPresent())
        return versionStatus.get();


        switch (codeOrName) {
            case "N":
                return New;
            case "A":
                return Amend;
            case "C":
                return Cancel;
            default:
                throw new IllegalArgumentException("Invalid code or name supplied as VersionStatus");
        }
    }

    public static boolean isActive(@NotNull VersionStatus status) {
        return New.equals(status) || Amend.equals(status);
    }

    public static List<VersionStatus> getLiveStatuses() {
      return Arrays.asList(VersionStatus.New, VersionStatus.Amend);
    }
}
