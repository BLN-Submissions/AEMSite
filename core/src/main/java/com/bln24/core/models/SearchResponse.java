package com.bln24.core.model;

/**
 Search response.
*/
public static class SearchResponse {
    private final List<SearchResult> results;
    private final int totalResults;
    
    public SearchResponse(List<SearchResult> results, int totalResults) {
        this.results = results;
        this.totalResults = totalResults;
    }
    
    public List<SearchResult> getResults() { return results; }
    public int getTotalResults() { return totalResults; }
   }