package com.bln24.core.services;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SocialMediaEmbedServiceTest {
   private SocialMediaEmbedService embedService;
   
   @BeforeEach
   void setUp() {
       embedService = new SocialMediaEmbedService();
   }
   
   @Test
   void testEmbedCodeForTwitter() {
       String url = "https://twitter.com/example";
       String embedCode = embedService.getEmbedCode(url);
       assertTrue(embedCode.contains("<blockquote class=\"twitter-tweet\">"));
   }
  
   @Test
   void testInvalidUrlReturnsEmpty() {
       String embedCode = embedService.getEmbedCode("https://unknown.com/example");
       assertEquals("", embedCode);
   }
}