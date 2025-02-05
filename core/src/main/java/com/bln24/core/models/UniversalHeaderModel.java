package com.bln24.core.models;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class UniversalHeaderModel {
   @ValueMapValue
   private String logoPath;
   
   @ValueMapValue
   private String logoLink;
   
   @ChildResource
   private Resource navigationItems;
   
   public String getLogoPath() {
       return logoPath;
   }
   
   public String getLogoLink() {
       return logoLink != null ? logoLink : "#";
   }
   
   public List<NavigationItem> getNavigationItems() {
       if (navigationItems != null) {
           return StreamSupport.stream(navigationItems.getChildren().spliterator(), false)
               .map(item -> new NavigationItem(
                       item.getValueMap().get("label", String.class),
                       item.getValueMap().get("link", String.class),
                       item.getValueMap().get("active", Boolean.class)))
               .collect(Collectors.toList());
       }
       return List.of();
   }
   
   public static class NavigationItem {
       private final String label;
       private final String link;
       private final boolean active;
       
       public NavigationItem(String label, String link, Boolean active) {
           this.label = label;
           this.link = link != null ? link : "#";
           this.active = active != null && active;
       }
       
       public String getLabel() { return label; }
       public String getLink() { return link; }
       public boolean isActive() { return active; }
   }
}