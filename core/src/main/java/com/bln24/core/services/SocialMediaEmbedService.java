package com.bln24.core.services;
import org.apache.commons.lang3.StringUtils;
import org.osgi.service.component.annotations.Component;
import java.util.HashMap;
import java.util.Map;

@Component(service = SocialMediaEmbedService.class)
public class SocialMediaEmbedService {
   private static final Map<String, String> EMBED_PATTERNS = new HashMap<>();
   
   static {
       EMBED_PATTERNS.put("twitter.com", "<blockquote class=\"twitter-tweet\"><a href=\"%s\"></a></blockquote>");
       EMBED_PATTERNS.put("instagram.com", "<blockquote class=\"instagram-media\"><a href=\"%s\"></a></blockquote>");
       EMBED_PATTERNS.put("facebook.com", "<iframe src=\"%s\" width=\"500\" height=\"300\"></iframe>");
   }
   
   public String getEmbedCode(String url) {
       if (StringUtils.isBlank(url)) return "";
       return EMBED_PATTERNS.entrySet().stream()
           .filter(entry -> url.contains(entry.getKey()))
           .map(entry -> String.format(entry.getValue(), url))
           .findFirst()
           .orElse("");
   }
}