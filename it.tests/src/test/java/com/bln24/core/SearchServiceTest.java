import com.bln24.core.services.SearchService;
import org.junit.Test;
import java.io.IOException;

import static org.junit.Assert.*;

public class SearchServiceTest {

    @Test
    public void testSearchService() throws IOException {
        SearchService service = new SearchService();
        
        // Mock a real external API call here or use a stub
        var results = service.search("test", 0, 10);
        
        assertNotNull(results);
        assertTrue(results.size() > 0);
    }
}
