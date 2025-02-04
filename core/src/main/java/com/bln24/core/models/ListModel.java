package com.bln24.core.models;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ListModel {
   @ValueMapValue
   private String listStyle;
   
   @ChildResource
   private Resource listItems;
   
   public String getListStyle() {
       return listStyle != null ? listStyle : "default";
   }
   
   public List<String> getListItems() {
       if (listItems != null) {
           return StreamSupport.stream(listItems.getChildren().spliterator(), false)
               .map(item -> item.getValueMap().get("itemText", String.class))
               .collect(Collectors.toList());
       }
       return List.of();
   }
}