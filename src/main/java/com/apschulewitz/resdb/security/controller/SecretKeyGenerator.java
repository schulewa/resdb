package com.apschulewitz.resdb.security.controller;

import java.util.Optional;

public class SecretKeyGenerator {

    public static void main(String[] args) {

        if (args.length != 1) {
            usage();
            System.exit(1);
        }

        String plainText = args[0];

        Optional<String> secret = JwtUtil.generateSecretKey(256, plainText);
        System.out.println("Generated secret:");
        System.out.println(secret.get());
    }

    public static void usage() {
        System.out.println("SecretKeyGenerator <plain-text>");
    }
}
