package com.bln24.core.services;
import com.bln24.core.config.SearchConfig;
import com.bln24.core.models.SearchResponse;
import com.bln24.core.models.SearchResult;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Component(service = SearchService.class)
@Designate(ocd = SearchConfig.class)
public class SearchService {
   private static final String API_URL = "https://api.externalsearch.com/v2/";
   private String apiKey;
   private String searchEngineCode;
   
   @Reference
   private SearchConfig config;
   
   @Activate
   @Modified
   protected void activate(SearchConfig config) {
       this.apiKey = config.apiKey();
       this.searchEngineCode = config.searchEngineCode();
   }
   /**
    * Fetches search results from the external API.
    */
   public SearchResponse fetchResults(String query, int offset, int size) throws IOException {
       String requestUrl = String.format(
           "%s?engine=%s&api_key=%s&query=%s&offset=%d&size=%d",
           API_URL, searchEngineCode, apiKey, query, offset, size
       );
       HttpURLConnection connection = (HttpURLConnection) new URL(requestUrl).openConnection();
       connection.setRequestMethod("GET");
       String response = IOUtils.toString(connection.getInputStream(), StandardCharsets.UTF_8);
       JSONObject jsonResponse = new JSONObject(response);
       JSONArray resultsArray = jsonResponse.getJSONArray("results");
       List<SearchResult> results = resultsArray.toList().stream()
           .map(obj -> (JSONObject) obj)
           .map(json -> new SearchResult(json.getString("title"), json.getString("snippet"), json.getString("url")))
           .collect(Collectors.toList());
       return new SearchResponse(results, jsonResponse.getInt("total"));
   } 
}