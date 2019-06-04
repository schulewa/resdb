package com.apschulewitz.resdb.security.controller;

import com.apschulewitz.resdb.security.exception.JwtBadSignatureException;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.apache.commons.lang3.StringUtils;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static com.nimbusds.jose.JWSAlgorithm.HS512;

public final class JwtUtil {

    private final static String AUDIENCE_UNKNOWN = "unknown";
    private final static String AUDIENCE_WEB = "web";
    private final static String AUDIENCE_MOBILE = "mobile";
    private final static String AUDIENCE_TABLE = "tablet";
    private final static String ROLES_CLAIM = "roles";

    private static Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    /**
     * <CODE>generateHMACToken</CODE> generates a HMAC token for the supplied arguments.
     * @param subject the unique identifier (principal) for the JWT claim
     * @param roles a Collection of roles for the supplied principal
     * @param secret a String containing the secret
     * @param expirationInMinutes an Integer containing the time in minutes before the JWT token will expire
     * @return a String containing the JWT token
     * @throws JOSEException
     */
    public static String generateHMACToken(String subject,
                                           Collection<? extends GrantedAuthority> roles,
                                           String secret,
                                           Integer expirationInMinutes) throws JOSEException {
        return generateHMACToken(subject, AuthorityListToCommaSeparatedString(roles), secret, expirationInMinutes);
    }

    /**
     * <CODE>generateHMACToken</CODE> generates a HMAC token for the supplied arguments.
     * @param subject a String holding the unique identifier (principal) for the JWT claim
     * @param roles a String containing the list of roles for the JWT claim, these may be comma separated
     * @param secret a String containing the secret
     * @param expirationInMinutes an Integer containing the time in minutes before the JWT token will expire
     * @return a String containing the JWT token
     * @throws JOSEException
     */
    public static String generateHMACToken(String subject, String roles, String secret, Integer expirationInMinutes) throws JOSEException {
        JWSSigner signer = new MACSigner(secret);
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(subject)
                .issueTime(currentDate())
//                    .expirationTime(expirationDate(expirationInMinutes)) // token renewal is not available and hence no expiry date is set
                .claim(ROLES_CLAIM, roles)
                .audience(AUDIENCE_WEB)
                .build();

        SignedJWT signedJWT = new SignedJWT(new JWSHeader(HS512), claimsSet);
        signedJWT.sign(signer);
        return signedJWT.serialize();
    }

    public static Optional<String> generateSecretKey(int minLength, String plainText) {
        KeyGenerator keyGen;
        SecretKey secretKey;

        try {
            keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(minLength);
            secretKey = keyGen.generateKey();

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] cipherText = cipher.doFinal(plainText.getBytes("UTF8"));
            return Optional.of(new String(cipherText));
        } catch (Exception e) {
            logger.error("Error generating secret key: " + e.getMessage(), e);
        }

        return Optional.empty();
    }

    private static Date currentDate() {
        return new Date(System.currentTimeMillis());
    }

    public static Date exppirationDate(Long expirationInMinutes) {
        return new Date(System.currentTimeMillis() + expirationInMinutes * 60 * 1000);
    }

    public static void assertNotExpired(SignedJWT jwt) throws ParseException {
        if (DateUtils.isBefore(jwt.getJWTClaimsSet().getExpirationTime(), currentDate(), 60));
    }

    public static void assertValidSignature(SignedJWT jwt, String secret) throws ParseException, JOSEException {
        if (!verifyHMACToken(jwt, secret)) {
            throw new JwtBadSignatureException("Signature is not valid");
        }
    }

    public static SignedJWT parse(String token) throws ParseException {
        return SignedJWT.parse(token);
    }

    public static boolean verifyHMACToken(SignedJWT jwt, String secret) throws ParseException, JOSEException {
        JWSVerifier verifier = new MACVerifier(secret);
        return jwt.verify(verifier);
    }

    private static String AuthorityListToCommaSeparatedString(Collection<? extends GrantedAuthority> authorities) {
        Set<String> authoritiesAsSetOfString = AuthorityUtils.authorityListToSet(authorities);
        return StringUtils.join(authoritiesAsSetOfString, ", ");
    }

    public static String getUsername(SignedJWT jwt) throws ParseException {
        return jwt.getJWTClaimsSet().getSubject();
    }

    public static Collection<? extends GrantedAuthority> getRoles(SignedJWT jwt) throws ParseException {
        Collection<? extends GrantedAuthority> authorities;
        String roles = jwt.getJWTClaimsSet().getStringClaim(ROLES_CLAIM);
        authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
        return authorities;
    }

    public static Date getIssueTime(SignedJWT jwt) throws ParseException {
        return jwt.getJWTClaimsSet().getIssueTime();
    }
}
