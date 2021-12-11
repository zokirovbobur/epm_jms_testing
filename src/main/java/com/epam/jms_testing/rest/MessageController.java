package com.epam.jms_testing.rest;

import com.epam.jms_testing.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class MessageController {

  private static final Logger log = LoggerFactory.getLogger(MessageController.class);

  private final JmsTemplate jmsTemplate;

  public MessageController(JmsTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;
  }

  @PostMapping("/send")
  public void send(@RequestBody Message transaction) {
    log.info("Sending a transaction.");
    jmsTemplate.convertAndSend(
        "MessageQueue", transaction);
  }

  @GetMapping("/test")
  public void test() {
    Message transaction = new Message();
    transaction.setContent("Hello brother");
    log.info("Sending a transaction.");
    jmsTemplate.convertAndSend(
            "MessageQueue", transaction);
  }
}
