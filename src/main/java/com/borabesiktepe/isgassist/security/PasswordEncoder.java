package com.borabesiktepe.isgassist.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

    //ENCODER TEST ALANI
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String rawPassword = "1234";
        String encoderPassword = encoder.encode(rawPassword);

        System.out.println(encoderPassword);
    }
}