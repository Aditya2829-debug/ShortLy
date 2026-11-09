package com.aditya.shortly.service;

import org.springframework.stereotype.Service;

@Service
public class UrlService {

    public String createShortUrl(String originalUrl) {
        return originalUrl;
    }

}
