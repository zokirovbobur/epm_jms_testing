package com.epam.jms_testing.booking;



import com.epam.jms_testing.dao.EventEntity;
import com.epam.jms_testing.dao.TicketEntity;
import com.epam.jms_testing.dao.UserEntity;
import com.epam.jms_testing.dto.GeneralAnswer;
import com.epam.jms_testing.dto.UserDto;

import java.util.List;

public interface BookingFacade {
	GeneralAnswer<TicketEntity> booking(Long attendeeId, Long eventId);
	GeneralAnswer<List<EventEntity>> listEvents();
	GeneralAnswer<List<TicketEntity>> listTickets();
	GeneralAnswer<UserEntity> signIn(String username, String password);
	GeneralAnswer<UserEntity> createUser(UserDto userDto);
}
