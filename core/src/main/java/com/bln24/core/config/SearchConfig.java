package com.bln24.core.config;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Search API Configuration")
public @interface SearchConfig {
   @AttributeDefinition(name = "Search API Key", description = "API key for external search API")
   String apiKey() default "";
   
   @AttributeDefinition(name = "Search Engine Code", description = "Search engine identifier for API")
   String searchEngineCode() default "searchgov";
}