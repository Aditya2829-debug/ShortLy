package com.aditya.shortly.controller;

import com.aditya.shortly.dto.CreateShortUrlRequest;
import com.aditya.shortly.service.UrlService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlController {

    private final UrlService urlService;
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/api/v1/urls")
    public String createShortUrl(@RequestBody CreateShortUrlRequest request){
        return urlService.createShortUrl(request.getUrl());
    }
}
