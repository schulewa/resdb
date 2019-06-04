package com.apschulewitz.resdb.security.controller;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.TextCodec;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;

//import io.jsonwebtoken.lang.Assert;
//import javax.annotation.PostConstruct;

@Service
public class SecretService {

    public SecretService() {
        secrets = new HashMap<>();
        refreshSecrets();
    }

    private Map<String, String> secrets; // = new HashMap<>();

    private SigningKeyResolver signingKeyResolver = new SigningKeyResolverAdapter() {
        @Override
        public byte[] resolveSigningKeyBytes(JwsHeader header, Claims claims) {
            return TextCodec.BASE64.decode(secrets.get(header.getAlgorithm()));
        }
    };

//    @PostConstruct
//    public void setup() {
//        refreshSecrets();
//    }

    public SigningKeyResolver getSigningKeyResolver() {
        return signingKeyResolver;
    }

    public Map<String, String> getSecrets() {
        return secrets;
    }

//    public void setSecrets(Map<String, String> secrets) {
//        Assert.notNull(secrets);
//        Assert.hasText(secrets.get(SignatureAlgorithm.HS256.getValue()));
//        Assert.hasText(secrets.get(SignatureAlgorithm.HS384.getValue()));
//        Assert.hasText(secrets.get(SignatureAlgorithm.HS512.getValue()));
//
//        this.secrets = secrets;
//    }

    public byte[] getHS256SecretBytes() {
        return TextCodec.BASE64.decode(secrets.get(SignatureAlgorithm.HS256.getValue()));
    }

    public byte[] getHS384SecretBytes() {
        return TextCodec.BASE64.decode(secrets.get(SignatureAlgorithm.HS384.getValue()));
    }

    public byte[] getHS512SecretBytes() {
        return TextCodec.BASE64.decode(secrets.get(SignatureAlgorithm.HS512.getValue()));
    }

    public Map<String, String> refreshSecrets() {
        SecretKey key = MacProvider.generateKey(SignatureAlgorithm.HS256);
        secrets.put(SignatureAlgorithm.HS256.getValue(), TextCodec.BASE64.encode(key.getEncoded()));
        key = MacProvider.generateKey(SignatureAlgorithm.HS384);
        secrets.put(SignatureAlgorithm.HS384.getValue(), TextCodec.BASE64.encode(key.getEncoded()));
        key = MacProvider.generateKey(SignatureAlgorithm.HS512);
        secrets.put(SignatureAlgorithm.HS512.getValue(), TextCodec.BASE64.encode(key.getEncoded()));
        return secrets;
    }
}
