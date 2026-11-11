package com.aditya.shortly.dto;

public class CreateShortUrlRequest {
    private String url;
    private String costumAlias;
    public String getCostumAlias() {
        return costumAlias;
    }

    public void setCostumAlias(String costumAlias) {
        this.costumAlias = costumAlias;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
