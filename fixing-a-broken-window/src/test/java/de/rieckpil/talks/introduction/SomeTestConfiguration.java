package de.rieckpil.talks.introduction;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@TestConfiguration
public class SomeTestConfiguration {

  @Bean
  public ObjectMapper customObjectMapper() {
    return Jackson2ObjectMapperBuilder.json().build();
  }
}
