package de.rieckpil.talks;


import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class InvoiceQueueListener {

  private static final Logger LOG = LoggerFactory.getLogger(InvoiceQueueListener.class);

  @SqsListener("${invoice-queue-name}")
  public void processIncomingOrders(String payload) {
    LOG.info("Incoming payload: '{}'", payload);
  }
}
