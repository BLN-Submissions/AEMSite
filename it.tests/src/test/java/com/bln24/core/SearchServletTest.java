import com.bln24.core.servlets.SearchServlet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.apache.sling.api.servlets.SlingHttpServletRequest;
import org.apache.sling.api.servlets.SlingHttpServletResponse;
import org.apache.sling.api.servlets.ServletException;

import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SearchServletTest {

    @Mock
    private SlingHttpServletRequest request;

    @Mock
    private SlingHttpServletResponse response;

    @Test
    public void testSearchServlet() throws ServletException, IOException {
        SearchServlet servlet = new SearchServlet();

        when(request.getParameter("query")).thenReturn("test");
        
        servlet.doGet(request, response);

        verify(response).setContentType("application/json");
        verify(response).getWriter();
    }
}
