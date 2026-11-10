package com.aditya.shortly.service;

import com.aditya.shortly.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

@Service
public class UrlService {

    private static final String BASE62_ALPHABET="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int CODE_LENGTH=8;

    private final SecureRandom secure=new SecureRandom();

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String createShortUrl(String originalUrl) {
        String shortcode= generateShortCode();
        urlRepository.saveUrl(shortcode,originalUrl);
        return shortcode;
    }
    private String generateShortCode(){
        StringBuilder code=new StringBuilder(CODE_LENGTH);
        for(int i=0;i<CODE_LENGTH;i++){
            int random=secure.nextInt(BASE62_ALPHABET.length());
            code.append(BASE62_ALPHABET.charAt(random));
        }
        return code.toString();
    }
    public Optional<String> getOriginalUrl(String shortCode){
        return urlRepository.getOriginalUrl(shortCode);
    }
}
