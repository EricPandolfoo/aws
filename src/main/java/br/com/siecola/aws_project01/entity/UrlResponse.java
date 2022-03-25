package br.com.siecola.aws_project01.entity;

public class UrlResponse {

    private String url;
    private long expirationTime;

    public UrlResponse(String url, long expirationTime) {
        this.url = url;
        this.expirationTime = expirationTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
    }
}
