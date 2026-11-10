package com.aditya.shortly.service;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class UrlService {

    private static final String BASE62_ALPHABET="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int CODE_LENGTH=8;

    private final SecureRandom secure=new SecureRandom();

    public String createShortUrl(String originalUrl) {
        return generateShortCode();
    }
    private String generateShortCode(){
        StringBuilder code=new StringBuilder(CODE_LENGTH);
        for(int i=0;i<CODE_LENGTH;i++){
            int random=secure.nextInt(BASE62_ALPHABET.length());
            code.append(BASE62_ALPHABET.charAt(random));
        }
        return code.toString();
    }
}
