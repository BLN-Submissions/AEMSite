import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SocialMediaComponentRenderingTest extends AemContext {

    @Test
    public void testSocialMediaComponentRendering() {
        // Mock the Sling Model with URLs
        SlingModels.newSlingModel("socialMediaModel", SocialMediaModel.class);
        
        // Set values for testing
        socialMediaModel.setYouTubeUrl("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
        socialMediaModel.setFacebookUrl("https://www.facebook.com/facebook");
        socialMediaModel.setTwitterUrl("https://twitter.com/Twitter");

        String htmlOutput = renderHTL("/apps/myproject/components/social-media/social-media.html");
        
        assertTrue(htmlOutput.contains("src=\"https://www.youtube.com/embed/dQw4w9WgXcQ\""));
        assertTrue(htmlOutput.contains("src=\"https://www.facebook.com/plugins/post.php?href=https://www.facebook.com/facebook\""));
        assertTrue(htmlOutput.contains("href=\"https://twitter.com/Twitter\""));
    }
}
