package com.bln24.core.models;
import com.bln24.core.services.SocialMediaEmbedService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import javax.inject.Inject;
import java.util.Optional;
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SocialMediaModel {
   @Inject
   @ValueMapValue
   private String embedUrl;
   @OSGiService
   private SocialMediaEmbedService embedService;
   public String getEmbedCode() {
       return embedService.getEmbedCode(embedUrl);
   }
   public boolean isValid() {
       return Optional.ofNullable(embedUrl).map(url -> url.startsWith("https://")).orElse(false);
   }
}