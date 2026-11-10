package com.aditya.shortly.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UrlRepository {

    private final JdbcTemplate jdbc;
    public UrlRepository(JdbcTemplate jdbc){
        this.jdbc=jdbc;
    }
    public void saveUrl(String shortCode,String originalUrl){
        String sql="INSERT INTO short_urls(short_code,original_url) " +
                "VALUES(?,?)";
        jdbc.update(sql,shortCode,originalUrl);
    }
}
