package com.epam.jms_testing.rest;

import com.epam.jms_testing.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {

  private static final Logger log = LoggerFactory.getLogger(MessageController.class);

  private final JmsTemplate jmsTemplate;

  public MessageController(JmsTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;
  }

  @PostMapping("/send")
  public void send(@RequestBody Message message) {
    log.info("Sending a message.");
    jmsTemplate.convertAndSend(
        "MessageQueue", message);
  }

  @GetMapping("/test")
  public void test() {
    Message message = new Message();
    message.setContent("Hello brother");
    log.info("Sending a message.");
    jmsTemplate.convertAndSend(
            "MessageQueue", message);
  }
}
