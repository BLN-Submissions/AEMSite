package com.bln24.core.services;
import static org.junit.jupiter.api.Assertions.*;
import com.bln24.core.config.SearchConfig;
import org.apache.sling.testing.mock.osgi.junit5.OsgiContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.IOException;
import java.util.List;
import static org.mockito.Mockito.*;

@ExtendWith(OsgiContext.class)
class SearchServiceTest {
   @InjectMocks
   private SearchService searchService;
   
   @Mock
   private SearchConfig searchConfig;
   
   @BeforeEach
   void setUp() {
       MockitoAnnotations.openMocks(this);
       when(searchConfig.apiKey()).thenReturn("mock-api-key");
       when(searchConfig.searchEngineCode()).thenReturn("mock-search-engine");
       searchService = new SearchService();
       searchService.activate(searchConfig);
   }
   
   @Test
   void testFetchResults() throws IOException {
       SearchService.SearchResponse response = searchService.fetchResults("AEM", 0, 10);
       assertNotNull(response);
       assertEquals(10, response.getResults().size());
   }
   
   @Test
   void testEmptyResults() throws IOException {
       SearchService.SearchResponse response = searchService.fetchResults("NoResults", 0, 10);
       assertNotNull(response);
       assertTrue(response.getResults().isEmpty());
   }
}