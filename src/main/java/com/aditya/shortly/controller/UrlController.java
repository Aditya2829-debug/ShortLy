package com.aditya.shortly.controller;

import com.aditya.shortly.dto.CreateShortUrlRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlController {

    @PostMapping("/api/v1/urls")
    public String CreateShortUrlRequest(@RequestBody CreateShortUrlRequest request){
        return request.getUrl();
    }
}
