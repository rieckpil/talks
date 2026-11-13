package de.rieckpil.talks.introduction;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

@TestConfiguration
public class SomeTestConfiguration {

  @Bean
  public ObjectMapper customObjectMapper() {
    return JsonMapper.builder().build();
  }
}
