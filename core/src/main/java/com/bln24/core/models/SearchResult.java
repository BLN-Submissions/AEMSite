package com.bln24.core.model;

/**
 Search Result.
*/
public static class SearchResult {
    private final String title;
    private final String snippet;
    private final String link;
    
    public SearchResult(String title, String snippet, String link) {
        this.title = title;
        this.snippet = snippet;
        this.link = link;
    }
    
    public String getTitle() { return title; }
    public String getSnippet() { return snippet; }
    public String getLink() { return link; }
   }
