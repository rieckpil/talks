package de.rieckpil.talks.solution;

import static de.rieckpil.talks.solution.ApplicationIT.localStack;

import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class MessagingQueueInitializer
  implements ApplicationContextInitializer<ConfigurableApplicationContext> {

  @Override
  public void initialize(@NotNull ConfigurableApplicationContext applicationContext) {
    String queueName = UUID.randomUUID().toString();

    try {
      localStack.execInContainer(
        "awslocal",
        "sqs",
        "create-queue",
        "--queue-name",
        queueName
      );
    } catch (Exception e) {
      throw new RuntimeException("Unable to initialize context", e);
    }

    TestPropertyValues.of("invoice-queue-name", queueName).applyTo(applicationContext);
  }
}
