package de.rieckpil.talks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class UpTimeScheduler {

  private static final Logger LOG = LoggerFactory.getLogger(UpTimeScheduler.class);

  private final ApplicationContext applicationContext;

  public UpTimeScheduler(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Scheduled(cron = "*/5 * * * * *")
  public void print() {
    LOG.info("Context '{} - {}' is still up", applicationContext.getId(), applicationContext);
  }
}
