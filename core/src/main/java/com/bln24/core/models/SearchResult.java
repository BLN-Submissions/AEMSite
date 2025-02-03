package com.bln24.core.model;

public class SearchResult {

    private String title;
    private String url;

    public SearchResult(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
