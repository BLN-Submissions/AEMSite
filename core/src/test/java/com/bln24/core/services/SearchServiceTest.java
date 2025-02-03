package com.bln24.core.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import java.io.IOException;
import static org.junit.Assert.*;

public class SearchServiceTest {

    @Mock
    private HttpClient mockHttpClient;

    @InjectMocks
    private SearchService searchService;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearchResults() throws IOException {
        // Mock the search.gov API response
        String apiResponse = "{\"response\":{\"docs\":[{\"title\":\"Test Result\",\"url\":\"https://example.com\"}]}}";
        when(mockHttpClient.get(anyString())).thenReturn(apiResponse);

        // Call the service method
        List<SearchResult> results = searchService.search("test", 0, 10);

        // Assertions
        assertEquals(1, results.size());
        assertEquals("Test Result", results.get(0).getTitle());
        assertEquals("https://example.com", results.get(0).getUrl());
    }
}
