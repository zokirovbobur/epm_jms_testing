package com.epam.jms_testing;

import com.epam.jms_testing.dao.EventEntity;
import com.epam.jms_testing.dao.UserEntity;
import com.epam.jms_testing.dto.TicketDto;
import com.epam.jms_testing.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.EventHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingIT {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private UserRepository userRepository;

	@Test
	public void testBooking() throws Exception {
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

		ObjectMapper mapper = new ObjectMapper();

		String content = mapper.writeValueAsString(ticketDto);

		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/booking").content(content))
				.andExpect(status().is2xxSuccessful());
	}
}
