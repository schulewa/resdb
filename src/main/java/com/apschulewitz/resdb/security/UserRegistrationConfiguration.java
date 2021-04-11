package com.apschulewitz.resdb.security;

import com.apschulewitz.resdb.common.exception.ResearchDatabaseCommonException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Configuration
@ConfigurationProperties(prefix = "user.registration")
@Data
public class UserRegistrationConfiguration {

  private static final String VERIFY_USER_SCHEME = "verify.url.scheme";
  private static final String VERIFY_USER_HOST = "verify.url.host";
  private static final String VERIFY_USER_PATH = "verify.url.path";

  private final Map<String, String> verifyUserUriProperties;

  public UserRegistrationConfiguration(@Value("${user.registration.verify.url.scheme}") String verifyUrlScheme,
                                       @Value("${user.registration.verify.url.host}") String verifyUrlHost,
                                       @Value("${user.registration.verify.url.path}") String verifyUrlPath
  ) {
      log.info("GOT HERE");
    if (StringUtils.isEmpty(verifyUrlScheme)) {
      throw new ResearchDatabaseCommonException("Verify user scheme not set in user registration config");
    }

    if (StringUtils.isEmpty(verifyUrlHost)) {
      throw new ResearchDatabaseCommonException("Verify user host not set in user registration config");
    }

    if (StringUtils.isEmpty(verifyUrlPath)) {
      throw new ResearchDatabaseCommonException("Verify user path not set in user registration config");
    }

    Map<String, String> verifyProps = new HashMap<>();
    verifyProps.put(VERIFY_USER_SCHEME, verifyUrlScheme);
    verifyProps.put(VERIFY_USER_HOST, verifyUrlHost);
    verifyProps.put(VERIFY_USER_PATH,verifyUrlPath);
    verifyUserUriProperties = Collections.unmodifiableMap(verifyProps);
  }

  @Bean
  public UriComponents verifyUserUrl() {
    String uuid = UUID.randomUUID().toString();
    return UriComponentsBuilder.newInstance()
      .scheme(verifyUserUriProperties.get(VERIFY_USER_SCHEME))
      .host(verifyUserUriProperties.get(VERIFY_USER_HOST))
      .path(verifyUserUriProperties.get(VERIFY_USER_PATH))
      .buildAndExpand(uuid);
  }
}
