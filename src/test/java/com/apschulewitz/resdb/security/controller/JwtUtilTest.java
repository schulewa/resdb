package com.apschulewitz.resdb.security.controller;

import com.apschulewitz.resdb.security.exception.JwtBadSignatureException;
import com.apschulewitz.resdb.security.model.entity.Permission;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;

public class JwtUtilTest {

  @Test
  public void given_valid_list_of_roles_when_generateHMACToken_is_executed_then_return_string() throws JOSEException {
    // Given
    String subject = "thesubject";

    Permission permission1 = Permission.builder()
      .name("perm1")
      .operationType(Permission.OperationType.Create)
      .status(Permission.PermissionStatus.Active)
      .build();
    Permission permission2 = Permission.builder()
      .name("perm2")
      .operationType(Permission.OperationType.Read)
      .status(Permission.PermissionStatus.Suspended)
      .build();
    Collection<? extends GrantedAuthority> roles = Arrays.asList(permission1, permission2);
    String secret = "ikkQ/uLv+AD9363StC0v4YEwYofj0vCpY9On4ANa2g9H6XKyAkaql6L/CoC+jlTu\\n\" +\n" +
      "      \"YqqRXj6sA4eOPM3VQLfvqcnnpIjRHqfub/eaohwDWBvC2P60aAuPchKwRP5wLjvz\\n\" +\n" +
      "      \"5XzLhq1nppY5NHQLGNDqDU68Y35kux/iF66YpvZ2q0bUpN8WXsY0aXPKitH7u1aW\\n\" +\n" +
      "      \"b7w+pMxH1ZojXTtezBr77vpe+VBwvoxZ617H0iHaad0G9+fd8zejdRWcVJCuIa4S\\n\" +\n" +
      "      \"7dtQR2mHTKgY9FsVOg59NtxkRU+qPByMiu2eXruHzT53ndG0rKWryQAKUb0rNUJ8\\n\" +\n" +
      "      \"utwWMhdKXKsR7bcLqkwCC/uaLXa3EbxvzFitBmbA2XDNB0Q=";
    Integer expirationInMinutes = 5;

    // When
    String hmac = JwtUtil.generateHMACToken(subject, roles, secret, expirationInMinutes);

    // Test
    assertNotNull(hmac);
  }

  @Test
  public void given_valid_csv_roles_when_generateHMACToken_is_executed_then_return_string() throws JOSEException {
    // Given
    String subject = "thesubject";

    Permission permission1 = Permission.builder()
      .name("perm1")
      .operationType(Permission.OperationType.Create)
      .status(Permission.PermissionStatus.Active)
      .build();
    Permission permission2 = Permission.builder()
      .name("perm2")
      .operationType(Permission.OperationType.Read)
      .status(Permission.PermissionStatus.Suspended)
      .build();
    String roles = permission1.toString() + "," + permission2.toString();

    String secret = "ikkQ/uLv+AD9363StC0v4YEwYofj0vCpY9On4ANa2g9H6XKyAkaql6L/CoC+jlTu\\n\" +\n" +
      "      \"YqqRXj6sA4eOPM3VQLfvqcnnpIjRHqfub/eaohwDWBvC2P60aAuPchKwRP5wLjvz\\n\" +\n" +
      "      \"5XzLhq1nppY5NHQLGNDqDU68Y35kux/iF66YpvZ2q0bUpN8WXsY0aXPKitH7u1aW\\n\" +\n" +
      "      \"b7w+pMxH1ZojXTtezBr77vpe+VBwvoxZ617H0iHaad0G9+fd8zejdRWcVJCuIa4S\\n\" +\n" +
      "      \"7dtQR2mHTKgY9FsVOg59NtxkRU+qPByMiu2eXruHzT53ndG0rKWryQAKUb0rNUJ8\\n\" +\n" +
      "      \"utwWMhdKXKsR7bcLqkwCC/uaLXa3EbxvzFitBmbA2XDNB0Q=";
    Integer expirationInMinutes = 5;

    // When
    String hmac = JwtUtil.generateHMACToken(subject, roles, secret, expirationInMinutes);

    // Test
    assertNotNull(hmac);
  }

  @Test
  public void given_valid_token_when_parse_is_executed_then_return_SignedJWT() throws JOSEException, ParseException {
    // Given
    String subject = "thesubject";

    Permission permission1 = Permission.builder()
      .name("perm1")
      .operationType(Permission.OperationType.Create)
      .status(Permission.PermissionStatus.Active)
      .build();
    Permission permission2 = Permission.builder()
      .name("perm2")
      .operationType(Permission.OperationType.Read)
      .status(Permission.PermissionStatus.Suspended)
      .build();
    String roles = permission1.toString() + "," + permission2.toString();

    String secret = "ikkQ/uLv+AD9363StC0v4YEwYofj0vCpY9On4ANa2g9H6XKyAkaql6L/CoC+jlTu\\n\" +\n" +
      "      \"YqqRXj6sA4eOPM3VQLfvqcnnpIjRHqfub/eaohwDWBvC2P60aAuPchKwRP5wLjvz\\n\" +\n" +
      "      \"5XzLhq1nppY5NHQLGNDqDU68Y35kux/iF66YpvZ2q0bUpN8WXsY0aXPKitH7u1aW\\n\" +\n" +
      "      \"b7w+pMxH1ZojXTtezBr77vpe+VBwvoxZ617H0iHaad0G9+fd8zejdRWcVJCuIa4S\\n\" +\n" +
      "      \"7dtQR2mHTKgY9FsVOg59NtxkRU+qPByMiu2eXruHzT53ndG0rKWryQAKUb0rNUJ8\\n\" +\n" +
      "      \"utwWMhdKXKsR7bcLqkwCC/uaLXa3EbxvzFitBmbA2XDNB0Q=";
    Integer expirationInMinutes = 5;
    String hmac = JwtUtil.generateHMACToken(subject, roles, secret, expirationInMinutes);

    // When
    SignedJWT signedJWT = JwtUtil.parse(hmac);

    // Test
    assertNotNull(signedJWT);
  }

  @Test
  public void given_minlen_and_plainText_when_generateSecretKey_is_executed_then_return_string() throws JOSEException {
    // Given
    int minlen = 256;
    String plainText = "the cat sat on the mat";

    // When
    Optional<String> secretKey = JwtUtil.generateSecretKey(minlen, plainText);

    // Then
    assertTrue(secretKey.isPresent());
  }

  @Test
  public void given_invalid_minlen_and_plainText_when_generateSecretKey_is_executed_then_return_error() throws InvalidParameterException {
    // Given
    int minlen = 64;
    String plainText = "the cat sat on the mat";

    // When
    Optional<String> secretKey = JwtUtil.generateSecretKey(minlen, plainText);

    // Test
    assertTrue(secretKey.isEmpty());
  }

  @Test
  public void given_expiration_when_exppirationDate_is_executed_then_return_Date() {
    // Given
    Long expirationInMinutes = 5L;

    // When
    Date exppirationDate = JwtUtil.exppirationDate(expirationInMinutes);

    // Then
    assertNotNull(exppirationDate);
  }

  @Test
  public void given_valid_token_when_assertValidSignature_is_executed_then_do_not_throw_exception() throws JOSEException, ParseException {
    // Given
    String subject = "thesubject";

    Permission permission1 = Permission.builder()
      .name("perm1")
      .operationType(Permission.OperationType.Create)
      .status(Permission.PermissionStatus.Active)
      .build();
    Permission permission2 = Permission.builder()
      .name("perm2")
      .operationType(Permission.OperationType.Read)
      .status(Permission.PermissionStatus.Suspended)
      .build();
    String roles = permission1.toString() + "," + permission2.toString();

    String secret = "ikkQ/uLv+AD9363StC0v4YEwYofj0vCpY9On4ANa2g9H6XKyAkaql6L/CoC+jlTu\\n\" +\n" +
      "      \"YqqRXj6sA4eOPM3VQLfvqcnnpIjRHqfub/eaohwDWBvC2P60aAuPchKwRP5wLjvz\\n\" +\n" +
      "      \"5XzLhq1nppY5NHQLGNDqDU68Y35kux/iF66YpvZ2q0bUpN8WXsY0aXPKitH7u1aW\\n\" +\n" +
      "      \"b7w+pMxH1ZojXTtezBr77vpe+VBwvoxZ617H0iHaad0G9+fd8zejdRWcVJCuIa4S\\n\" +\n" +
      "      \"7dtQR2mHTKgY9FsVOg59NtxkRU+qPByMiu2eXruHzT53ndG0rKWryQAKUb0rNUJ8\\n\" +\n" +
      "      \"utwWMhdKXKsR7bcLqkwCC/uaLXa3EbxvzFitBmbA2XDNB0Q=";
    Integer expirationInMinutes = 5;
    String hmac = JwtUtil.generateHMACToken(subject, roles, secret, expirationInMinutes);
    SignedJWT signedJWT = JwtUtil.parse(hmac);
    // When
    JwtUtil.assertValidSignature(signedJWT, secret);

    // Then
    assertNotNull(signedJWT);
  }

  @Test(expected = JwtBadSignatureException.class)
  public void given_valid_token_when_assertValidSignature_is_executed_then_throw_exception() throws JOSEException, ParseException {
    // Given
    String subject = "thesubject";

    Permission permission1 = Permission.builder()
      .name("perm1")
      .operationType(Permission.OperationType.Create)
      .status(Permission.PermissionStatus.Active)
      .build();
    Permission permission2 = Permission.builder()
      .name("perm2")
      .operationType(Permission.OperationType.Read)
      .status(Permission.PermissionStatus.Suspended)
      .build();
    String roles = permission1.toString() + "," + permission2.toString();

    String secret = "ikkQ/uLv+AD9363StC0v4YEwYofj0vCpY9On4ANa2g9H6XKyAkaql6L/CoC+jlTu\\n\" +\n" +
      "      \"YqqRXj6sA4eOPM3VQLfvqcnnpIjRHqfub/eaohwDWBvC2P60aAuPchKwRP5wLjvz\\n\" +\n" +
      "      \"5XzLhq1nppY5NHQLGNDqDU68Y35kux/iF66YpvZ2q0bUpN8WXsY0aXPKitH7u1aW\\n\" +\n" +
      "      \"b7w+pMxH1ZojXTtezBr77vpe+VBwvoxZ617H0iHaad0G9+fd8zejdRWcVJCuIa4S\\n\" +\n" +
      "      \"7dtQR2mHTKgY9FsVOg59NtxkRU+qPByMiu2eXruHzT53ndG0rKWryQAKUb0rNUJ8\\n\" +\n" +
      "      \"utwWMhdKXKsR7bcLqkwCC/uaLXa3EbxvzFitBmbA2XDNB0Q=";
    Integer expirationInMinutes = 5;
    String hmac = JwtUtil.generateHMACToken(subject, roles, secret, expirationInMinutes);
    SignedJWT signedJWT = JwtUtil.parse(hmac);
    // When
    JwtUtil.assertValidSignature(signedJWT, secret+"LETS_INVALIDATE_IT");

    // Then
    assertNotNull(signedJWT);
  }

  @Test
  public void given_valid_token_when_getUsername_is_executed_then_return_subject() throws JOSEException, ParseException {
    // Given
    String subject = "thesubject";

    Permission permission1 = Permission.builder()
      .name("perm1")
      .operationType(Permission.OperationType.Create)
      .status(Permission.PermissionStatus.Active)
      .build();
    Permission permission2 = Permission.builder()
      .name("perm2")
      .operationType(Permission.OperationType.Read)
      .status(Permission.PermissionStatus.Suspended)
      .build();
    String roles = permission1.toString() + "," + permission2.toString();

    String secret = "ikkQ/uLv+AD9363StC0v4YEwYofj0vCpY9On4ANa2g9H6XKyAkaql6L/CoC+jlTu\\n\" +\n" +
      "      \"YqqRXj6sA4eOPM3VQLfvqcnnpIjRHqfub/eaohwDWBvC2P60aAuPchKwRP5wLjvz\\n\" +\n" +
      "      \"5XzLhq1nppY5NHQLGNDqDU68Y35kux/iF66YpvZ2q0bUpN8WXsY0aXPKitH7u1aW\\n\" +\n" +
      "      \"b7w+pMxH1ZojXTtezBr77vpe+VBwvoxZ617H0iHaad0G9+fd8zejdRWcVJCuIa4S\\n\" +\n" +
      "      \"7dtQR2mHTKgY9FsVOg59NtxkRU+qPByMiu2eXruHzT53ndG0rKWryQAKUb0rNUJ8\\n\" +\n" +
      "      \"utwWMhdKXKsR7bcLqkwCC/uaLXa3EbxvzFitBmbA2XDNB0Q=";
    Integer expirationInMinutes = 5;
    String hmac = JwtUtil.generateHMACToken(subject, roles, secret, expirationInMinutes);
    SignedJWT signedJWT = JwtUtil.parse(hmac);

    // When
    String username = JwtUtil.getUsername(signedJWT);

    // Then
    assertNotNull(username);
    assertEquals(subject, username);
  }

  @Test
  public void given_valid_token_when_getRoles_is_executed_then_return_roles() throws JOSEException, ParseException {
    // Given
    String subject = "thesubject";

    Permission permission1 = Permission.builder()
      .name("perm1")
      .operationType(Permission.OperationType.Create)
      .status(Permission.PermissionStatus.Active)
      .build();
    Permission permission2 = Permission.builder()
      .name("perm2")
      .operationType(Permission.OperationType.Read)
      .status(Permission.PermissionStatus.Suspended)
      .build();
    String roles = permission1.toString() + "," + permission2.toString();

    String secret = "ikkQ/uLv+AD9363StC0v4YEwYofj0vCpY9On4ANa2g9H6XKyAkaql6L/CoC+jlTu\\n\" +\n" +
      "      \"YqqRXj6sA4eOPM3VQLfvqcnnpIjRHqfub/eaohwDWBvC2P60aAuPchKwRP5wLjvz\\n\" +\n" +
      "      \"5XzLhq1nppY5NHQLGNDqDU68Y35kux/iF66YpvZ2q0bUpN8WXsY0aXPKitH7u1aW\\n\" +\n" +
      "      \"b7w+pMxH1ZojXTtezBr77vpe+VBwvoxZ617H0iHaad0G9+fd8zejdRWcVJCuIa4S\\n\" +\n" +
      "      \"7dtQR2mHTKgY9FsVOg59NtxkRU+qPByMiu2eXruHzT53ndG0rKWryQAKUb0rNUJ8\\n\" +\n" +
      "      \"utwWMhdKXKsR7bcLqkwCC/uaLXa3EbxvzFitBmbA2XDNB0Q=";
    Integer expirationInMinutes = 5;
    String hmac = JwtUtil.generateHMACToken(subject, roles, secret, expirationInMinutes);
    SignedJWT signedJWT = JwtUtil.parse(hmac);

    // When
    Collection<? extends GrantedAuthority> grantedAuthorities = JwtUtil.getRoles(signedJWT);

    // Then
    assertNotNull(grantedAuthorities);
  }

  @Test
  public void given_valid_token_when_getIssueTime_is_executed_then_return_date() throws JOSEException, ParseException {
    // Given
    String subject = "thesubject";

    Permission permission1 = Permission.builder()
      .name("perm1")
      .operationType(Permission.OperationType.Create)
      .status(Permission.PermissionStatus.Active)
      .build();
    Permission permission2 = Permission.builder()
      .name("perm2")
      .operationType(Permission.OperationType.Read)
      .status(Permission.PermissionStatus.Suspended)
      .build();
    String roles = permission1.toString() + "," + permission2.toString();

    String secret = "ikkQ/uLv+AD9363StC0v4YEwYofj0vCpY9On4ANa2g9H6XKyAkaql6L/CoC+jlTu\\n\" +\n" +
      "      \"YqqRXj6sA4eOPM3VQLfvqcnnpIjRHqfub/eaohwDWBvC2P60aAuPchKwRP5wLjvz\\n\" +\n" +
      "      \"5XzLhq1nppY5NHQLGNDqDU68Y35kux/iF66YpvZ2q0bUpN8WXsY0aXPKitH7u1aW\\n\" +\n" +
      "      \"b7w+pMxH1ZojXTtezBr77vpe+VBwvoxZ617H0iHaad0G9+fd8zejdRWcVJCuIa4S\\n\" +\n" +
      "      \"7dtQR2mHTKgY9FsVOg59NtxkRU+qPByMiu2eXruHzT53ndG0rKWryQAKUb0rNUJ8\\n\" +\n" +
      "      \"utwWMhdKXKsR7bcLqkwCC/uaLXa3EbxvzFitBmbA2XDNB0Q=";
    Integer expirationInMinutes = 5;
    String hmac = JwtUtil.generateHMACToken(subject, roles, secret, expirationInMinutes);
    SignedJWT signedJWT = JwtUtil.parse(hmac);

    // When
    Date issueTime = JwtUtil.getIssueTime(signedJWT);

    // Then
    assertNotNull(issueTime);
  }

}
