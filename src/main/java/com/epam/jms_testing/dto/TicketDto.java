package com.epam.jms_testing.dto;

import com.epam.jms_testing.dao.EventEntity;
import com.epam.jms_testing.dao.TicketEntity;
import com.epam.jms_testing.dao.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {
	private EventEntity event;
	private UserEntity attendee;

	public TicketEntity toEntity(){
		return new TicketEntity(event, attendee);
	}
}
