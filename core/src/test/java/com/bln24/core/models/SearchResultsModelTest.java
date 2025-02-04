package com.bln24.core.models;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.bln24.core.services.SearchService;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import io.wcm.testing.mock.aem.junit5.AemContext;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;

@ExtendWith(AemContextExtension.class)
class SearchResultsModelTest {
   private final AemContext context = new AemContext();
   
   @InjectMocks
   private SearchResultsModel model;
   
   @Mock
   private SearchService searchService;
   
   @BeforeEach
   void setUp() {
       MockitoAnnotations.openMocks(this);
       context.addModelsForClasses(SearchResultsModel.class);
       context.create().resource("/content/searchresults",
               "defaultQuery", "AEM",
               "pageSize", 10,
               "currentPage", 1);
       Resource resource = context.resourceResolver().getResource("/content/searchresults");
       model = resource.adaptTo(SearchResultsModel.class);
       when(searchService.fetchResults(anyString(), anyInt(), anyInt()))
               .thenReturn(new SearchService.SearchResponse(
                       List.of(new SearchService.SearchResult("Test Title", "Test Snippet", "https://test.com")),
                       1
               ));
   }
   
   @Test
   void testHasResults() {
       assertTrue(model.hasResults());
   }
   
   @Test
   void testGetResults() {
       assertNotNull(model.getResults());
       assertEquals(1, model.getResults().size());
       assertEquals("Test Title", model.getResults().get(0).getTitle());
   }
   
   @Test
   void testPagination() {
       assertEquals(1, model.getTotalPages());
       assertEquals(1, model.getCurrentPage());
   }
}