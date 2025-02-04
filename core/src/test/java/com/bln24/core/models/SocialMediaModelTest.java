package com.bln24.core.models;
import static org.junit.jupiter.api.Assertions.*;
import com.bln24.core.services.SocialMediaEmbedService;
import com.bln24.core.models.SocialMediaModel;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import io.wcm.testing.mock.aem.junit5.AemContext;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

@ExtendWith(AemContextExtension.class)
class SocialMediaModelTest {
   private final AemContext context = new AemContext();
   
   @InjectMocks
   private SocialMediaModel model;
   
   @Mock
   private SocialMediaEmbedService embedService;
   
   @BeforeEach
   void setUp() {
       MockitoAnnotations.openMocks(this);
       context.addModelsForClasses(SocialMediaModel.class);
       context.create().resource("/content/social", "embedUrl", "https://twitter.com/example");
       Resource resource = context.resourceResolver().getResource("/content/social");
       model = resource.adaptTo(SocialMediaModel.class);
       when(embedService.getEmbedCode(anyString())).thenReturn("<blockquote>Mocked Embed</blockquote>");
   }
   
   @Test
   void testValidEmbedUrl() {
       assertNotNull(model);
       assertTrue(model.isValid());
       assertEquals("<blockquote>Mocked Embed</blockquote>", model.getEmbedCode());
   }
}