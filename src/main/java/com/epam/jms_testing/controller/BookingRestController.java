package com.epam.jms_testing.controller;


import com.epam.jms_testing.booking.BookingFacade;
import com.epam.jms_testing.dao.EventEntity;
import com.epam.jms_testing.dao.UserEntity;
import com.epam.jms_testing.dto.EventsInit;
import com.epam.jms_testing.dto.GeneralAnswer;
import com.epam.jms_testing.dto.TicketDto;
import com.epam.jms_testing.repository.UserRepository;
import com.epam.jms_testing.rest.MessageController;
import com.epam.jms_testing.service.EventService;
import com.epam.jms_testing.service.FilesStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("api")
public class BookingRestController {
	private static final Logger log = LoggerFactory.getLogger(MessageController.class);

	private final EventService eventService;
	private final BookingFacade bookingService;
	private final JmsTemplate jmsTemplate;
	private final FilesStorageService storageService;
	@Autowired
	private UserRepository userRepository;

	public BookingRestController(EventService eventService, BookingFacade bookingService, JmsTemplate jmsTemplate, FilesStorageService storageService) {
		this.eventService = eventService;
		this.bookingService = bookingService;
		this.jmsTemplate = jmsTemplate;
		this.storageService = storageService;
	}

	@PostMapping("/events/add")
	public ResponseEntity<GeneralAnswer> loadData(@RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			String uri = storageService.save(file);
			List<EventEntity> eventEntities = EventsInit.convertXMLToEvents(uri);
			eventEntities.forEach(event -> eventService.save(event));

			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new GeneralAnswer(message));
		} catch (Exception e) {
			e.printStackTrace();
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new GeneralAnswer(message));
		}
	}

	@GetMapping("booking/test")
	public ResponseEntity<GeneralAnswer> test(){
		TicketDto ticketDto = new TicketDto();
		EventEntity eventEntity = new EventEntity();
		eventEntity.setId(1L);
		ticketDto.setEvent(eventEntity);
		UserEntity user = new UserEntity();
		user.setId(1L);
		user.setUserName("John");
		user.setFullName("John Doe");

		user = userRepository.save(user);

		ticketDto.setAttendee(user);
		jmsTemplate.convertAndSend("TicketQueue", ticketDto);
		return ResponseEntity.ok(new GeneralAnswer<>("ticket sent"));
	}

	@PostMapping("booking")
	public ResponseEntity<GeneralAnswer> booking(TicketDto ticketDto){
		jmsTemplate.convertAndSend("TicketQueue", ticketDto);
		return ResponseEntity.ok(new GeneralAnswer<>("ticket sent"));
	}
}
