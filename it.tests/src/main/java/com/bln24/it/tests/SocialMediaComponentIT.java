package com.bln24.it.tests;
import static org.junit.jupiter.api.Assertions.*;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import io.wcm.testing.mock.aem.junit5.AemContext;
import org.apache.sling.api.resource.Resource;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(AemContextExtension.class)
public class SocialMediaComponentIT {
   private final AemContext context = new AemContext();
   
   @BeforeEach
   public void setUp() {
       context.create().resource("/content/social", "embedUrl", "https://twitter.com/example");
   }
   
   @Test
   public void testSocialMediaComponentRendering() {
       Resource resource = context.resourceResolver().getResource("/content/social");
       assertNotNull(resource);
       String embedUrl = resource.getValueMap().get("embedUrl", String.class);
       assertEquals("https://twitter.com/example", embedUrl);
       SocialMediaGraphQLService graphQLService = context.getService(SocialMediaGraphQLService.class);
       JSONObject metadata = graphQLService.fetchMetadata(embedUrl);
       assertNotNull(metadata);
       assertNotNull(metadata.getString("title"));
   }
}