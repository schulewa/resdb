package com.apschulewitz.resdb.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Data
@Slf4j
public class UserAuthenticationConfiguration {

  public static final String ENCODE_METHOD = "bcrypt";

  @Value("${authentication.strength:12}")
  private Integer authenticationStrength;

  @Bean
  public PasswordEncoder encoder() {
    Map<String,PasswordEncoder> encoders = new HashMap<>();
    encoders.put(ENCODE_METHOD, new BCryptPasswordEncoder(authenticationStrength));
    encoders.put("noop", NoOpPasswordEncoder.getInstance());
    encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
    encoders.put("scrypt", new SCryptPasswordEncoder());
    encoders.put("sha256", new StandardPasswordEncoder());
    PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(ENCODE_METHOD, encoders);
    return passwordEncoder;
  }
}
