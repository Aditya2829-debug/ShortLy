package com.aditya.shortly.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    public Optional<String> getOriginalUrl(String shortCode) {
        String sql = "SELECT original_url from short_urls WHERE short_code=?";
        List<String> res = jdbc.query(sql, (rs, rowNum) -> rs.getString("original_url"), shortCode);
        return res.stream().findFirst();
    }

}
