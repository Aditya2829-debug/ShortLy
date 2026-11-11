package com.aditya.shortly.controller;

import com.aditya.shortly.dto.CreateShortUrlRequest;
import com.aditya.shortly.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.net.URI;

@RestController
public class UrlController {

    private final UrlService urlService;
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/api/v1/urls")
    public String createShortUrl(@RequestBody CreateShortUrlRequest request){
        return urlService.createShortUrl(request.getUrl(),request.getCostumAlias());
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable("shortCode") String shortCode){
        String originalUrl=urlService.getOriginalUrl(shortCode)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Short URL not found"
                ));
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
    }
}
