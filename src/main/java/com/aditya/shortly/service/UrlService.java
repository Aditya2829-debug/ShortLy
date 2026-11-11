package com.aditya.shortly.service;

import com.aditya.shortly.exception.DuplicateAliasException;
import com.aditya.shortly.exception.InvalidUrlException;
import com.aditya.shortly.repository.UrlRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
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

    public String createShortUrl(String originalUrl,String costumAlias) {
        validateUrl(originalUrl);
        String shortCode;
        if(costumAlias!=null && !costumAlias.isBlank())
            shortCode=costumAlias;
        else
            shortCode= generateShortCode();
        try {
            urlRepository.saveUrl(shortCode, originalUrl);
        }
        catch(DuplicateKeyException ex){
            if (costumAlias != null && !costumAlias.isBlank()) {
                throw new DuplicateAliasException("Alias already exists");
            }
        }
        return shortCode;
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

    private void validateUrl(String original_url) {
        if(original_url == null || original_url.isBlank()){
            throw new InvalidUrlException("Invalid Url");
        }
        try {
            URI uri=new URI(original_url);
            String scheme=uri.getScheme();
            if(scheme==null|| !scheme.equalsIgnoreCase("https")&&
                    !scheme.equalsIgnoreCase("http")){
                throw new InvalidUrlException("Only HTTP and HTTPS URLs are allowed");
            }
            if(uri.getHost()==null){
                throw new InvalidUrlException("Invalid Host");
            }

        } catch (URISyntaxException e) {
            throw new InvalidUrlException("Only valid URL is allowed");
        }
    }
}