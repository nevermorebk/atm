package com.homdirect.application.transformer;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordEncryption {
    public static String encrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}