package de.rieckpil.talks.introduction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class CustomInitializer
  implements ApplicationContextInitializer<ConfigurableApplicationContext> {

  private static final Logger LOG = LoggerFactory.getLogger(CustomInitializer.class);

  @Override
  public void initialize(ConfigurableApplicationContext applicationContext) {
    LOG.info("Doing init work on context startup");
  }
}
