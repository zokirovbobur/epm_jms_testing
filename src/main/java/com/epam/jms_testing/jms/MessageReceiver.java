package com.epam.jms_testing.jms;

import com.epam.jms_testing.entity.Message;
import com.epam.jms_testing.repositories.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

  private static final Logger log = LoggerFactory.getLogger(MessageReceiver.class);

  private final MessageRepository transactionRepository;

  public MessageReceiver(MessageRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  private int count = 1;

  @JmsListener(destination = "MessageQueue", containerFactory = "myFactory")
  public void receiveMessage(Message message) {
    log.info("<" + count + "> Received <" + message + ">");
    count++;
    //    throw new RuntimeException();
    transactionRepository.save(message);
  }
}
