package com.epam.jms_testing.jms;

import com.epam.jms_testing.dto.EventDto;
import com.epam.jms_testing.dto.TicketDto;
import com.epam.jms_testing.dto.UserDto;
import com.epam.jms_testing.entity.Message;
import com.epam.jms_testing.repository.MessageRepository;
import com.epam.jms_testing.service.EventService;
import com.epam.jms_testing.service.TicketService;
import com.epam.jms_testing.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
public class MessageReceiver {

    private static final Logger log = LoggerFactory.getLogger(MessageReceiver.class);

    private final MessageRepository messageRepository;
    private final EventService eventService;
    private final TicketService ticketService;
    private final UserService userService;

    public MessageReceiver(MessageRepository transactionRepository, EventService eventService, TicketService ticketService, UserService userService) {
        this.messageRepository = transactionRepository;
        this.eventService = eventService;
        this.ticketService = ticketService;
        this.userService = userService;
    }

    private int count = 1;

    @JmsListener(destination = "MessageQueue", containerFactory = "myFactory")
    public void receiveMessage(Message message) {
        String incomingMessage = "<" + count + "> Received <" + message + ">";
        log.info(incomingMessage);
        count++;
        messageRepository.save(message);
    }

    @JmsListener(destination = "EventQueue", containerFactory = "myFactory")
    public void receiveEvent(EventDto eventDto) {
        String incomingMessage = "<" + count + "> Received <" + eventDto + ">";
        log.info(incomingMessage);
        count++;
        eventService.save(eventDto.toEntity());
        db_log(incomingMessage);
    }

    @JmsListener(destination = "TicketQueue", containerFactory = "myFactory")
    public void receiveTicket(TicketDto ticket) {
        String incomingMessage = "<" + count + "> Received <" + ticket + ">";
        log.info(incomingMessage);
        count++;
        ticketService.save(ticket.toEntity());
        db_log(incomingMessage);
    }

    @JmsListener(destination = "UserQueue", containerFactory = "myFactory")
    public void receiveUser(UserDto userDto) {
        String incomingMessage = "<" + count + "> Received <" + userDto + ">";
        log.info(incomingMessage);
        count++;
        userService.save(userDto.toEntity());
        db_log(incomingMessage);
    }

    private void db_log(String content) {
        messageRepository.save(new Message(content));
    }
}
