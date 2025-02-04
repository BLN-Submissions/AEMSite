package com.bln24.core.models;
import com.bln24.core.services.SearchService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.*;
import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SearchResultsModel {
   @Inject @ValueMapValue private String defaultQuery;
   @Inject @ValueMapValue private int pageSize;
   @Inject @ValueMapValue private int currentPage;
   @OSGiService private SearchService searchService;
   private SearchService.SearchResponse searchResponse;
   
   public List<SearchService.SearchResult> getResults() {
       try {
           int offset = (currentPage - 1) * pageSize;
           searchResponse = searchService.fetchResults(defaultQuery, offset, pageSize);
           return searchResponse.getResults();
       } catch (Exception e) {
           return List.of();
       }
   }
   
   public boolean hasResults() { return searchResponse != null && !searchResponse.getResults().isEmpty(); }
   public int getTotalResults() { return searchResponse != null ? searchResponse.getTotalResults() : 0; }
   public int getTotalPages() { return (int) Math.ceil((double) getTotalResults() / pageSize); }
   public int getCurrentPage() { return currentPage; }
}