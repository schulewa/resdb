package com.apschulewitz.resdb.security.model.filter;


import com.apschulewitz.resdb.security.model.entity.Permission;
import com.apschulewitz.resdb.security.model.entity.UserGroupMembership;
import com.apschulewitz.resdb.security.model.entity.UserGroupPermission;

import java.time.LocalDateTime;
import java.util.function.Predicate;

public class UserPredicates {

    public static Predicate<UserGroupMembership> hasGroupPermissions() {
        return (UserGroupMembership m) -> (m != null) &&
                (m.getGroup() != null) && (m.getGroup().getGroupPermissions() != null) &&
                !m.getGroup().getGroupPermissions().isEmpty();
    }

    public static Predicate<UserGroupMembership> isActiveGroup() {
        LocalDateTime now = LocalDateTime.now();
        return (UserGroupMembership m) -> m != null &&
                (now.isAfter(m.getValidFrom()) || now.isEqual(m.getValidFrom())) && (m.getValidTo() == null || now.isBefore(m.getValidTo()));
    }

    public static Predicate<UserGroupPermission> isActiveGroupPermission() {
      return (UserGroupPermission p) -> p != null &&
              p.getPermission() != null && Permission.PermissionStatus.Active.equals(p.getPermission().getStatus());
    }
}
