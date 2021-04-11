package com.apschulewitz.resdb.common;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
@Getter
public class EmailConfiguration {

  public static final String EMAIL_TEMPLATES_PATH = "templates/email/";
  public static final String USER_REGISTRATION_EMAIL_TEMPLATE = "user_registration";

  private String emailHost;
  private String emailPort;
  private String emailUsername;
  private String emailPassword;
  private String emailDefaultFrom;

  public EmailConfiguration(
    @Value("${spring.mail.host}") String host,
    @Value("${spring.mail.port}") String port,
    @Value("${spring.mail.username}") String username,
    @Value("${spring.mail.password}") String password,
    @Value("${spring.mail.defaultFromEmail}") String defaultFrom) {
    this.emailHost = host;
    this.emailPort = port;
    this.emailUsername = username;
    this.emailPassword = password;
    this.emailDefaultFrom = defaultFrom;
  }

  @Bean
  public SimpleMailMessage verifyUserEmailTemplate() {
    SimpleMailMessage message = new SimpleMailMessage();
    String unformattedMessage = String.format("%s %s\n%s\n\n%s",
      "Please click the following link to verify your email address",
      "or copy and past the link into your browser url:",
      "${verify_user_url}",
      "This link will expire at ${expiry_timestamp}.");
    message.setText(unformattedMessage);
    return message;
  }

  @Bean
  public SimpleMailMessage resetPasswordEmailTemplate() {
    SimpleMailMessage message = new SimpleMailMessage();
    String text = String.format("%s %s\n%s\n\n%s",
      "Please click the following link to reset your password",
      "or copy and past the link into your browser url:",
      "${reset_password_url}",
      "This link will expire at ${expiry_timestamp}.");
    message.setText(text);
    return message;
  }

  @Bean
  public FreeMarkerConfigurer freeMarkerConfigurer() {
    FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
    freeMarkerConfigurer.setTemplateLoaderPath(EMAIL_TEMPLATES_PATH);
    return freeMarkerConfigurer;
  }

  @Bean
  public FreeMarkerViewResolver freeMarkerViewResolver() {
    FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
    resolver.setCache(true);
    resolver.setPrefix("");
    resolver.setSuffix(".ftl");
    return resolver;
  }
}
