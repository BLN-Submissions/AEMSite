package com.bln24.core.models;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import io.wcm.testing.mock.aem.junit5.AemContext;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

class ListModelTest {
   private final AemContext context = new AemContext();
   private ListModel listModel;
   
   @BeforeEach
   void setUp() {
       context.addModelsForClasses(ListModel.class);
       context.load().json("/com/mycompany/core/models/list.json", "/content/list");
       Resource resource = context.resourceResolver().getResource("/content/list");
       assertNotNull(resource);
       listModel = resource.adaptTo(ListModel.class);
   }

   @Test
   void testGetListStyle() {
       assertNotNull(listModel);
       assertEquals("grid", listModel.getListStyle(), "List style should be 'grid'");
   }

   @Test
   void testGetListItems() {
       assertNotNull(listModel);
       List<String> items = listModel.getListItems();
       assertNotNull(items);
       assertEquals(3, items.size(), "There should be 3 list items");
       assertEquals("Item 1", items.get(0));
       assertEquals("Item 2", items.get(1));
       assertEquals("Item 3", items.get(2));
   }
}