package com.bln24.it.tests;
import static org.junit.jupiter.api.Assertions.*;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import io.wcm.testing.mock.aem.junit5.AemContext;
import org.apache.sling.api.resource.Resource;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@ExtendWith(AemContextExtension.class)
public class SearchResultsComponentIT {
   private final AemContext context = new AemContext();
   
   @BeforeEach
   public void setUp() {
       context.create().resource("/content/searchresults",
               "defaultQuery", "AEM",
               "pageSize", 10,
               "currentPage", 1);
   }
   
   @Test
   public void testSearchResultsRendering() {
       Resource resource = context.resourceResolver().getResource("/content/searchresults");
       assertNotNull(resource);
       String defaultQuery = resource.getValueMap().get("defaultQuery", String.class);
       assertEquals("AEM", defaultQuery);
   }
   
   @Test
   public void testSearchAPIEndpoint() throws IOException {
       URL url = new URL("http://localhost:4502/bin/search?query=AEM&page=1");
       HttpURLConnection connection = (HttpURLConnection) url.openConnection();
       connection.setRequestMethod("GET");
       assertEquals(200, connection.getResponseCode());
       String response = new String(connection.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
       JSONObject jsonResponse = new JSONObject(response);
       assertTrue(jsonResponse.has("results"));
   }
}