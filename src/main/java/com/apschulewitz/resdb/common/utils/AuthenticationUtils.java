package com.apschulewitz.resdb.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationUtils {

  public String getCurrentAuthenticatedUser() {
    String username = null;
    Object principal = null;
    if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null)  {
      principal = SecurityContextHolder.getContext().getAuthentication();
    }
    if (principal instanceof UserDetails) {
      username = ((UserDetails) principal).getUsername();
    } else if (principal instanceof UsernamePasswordAuthenticationToken) {
      Object obj = ((UsernamePasswordAuthenticationToken)principal).getPrincipal();
      if (obj instanceof User) {
        username = ((User)obj).getUsername();
      } else if (obj instanceof UserDetails) {
        username = ((UserDetails) principal).getUsername();
      } else {
        log.warn("Principal is an unrecognized type of object [{}], unable to extract current username", obj);
      }
    } else if (principal != null) {
      username = principal.toString();
    }
    return username;
  }
}
