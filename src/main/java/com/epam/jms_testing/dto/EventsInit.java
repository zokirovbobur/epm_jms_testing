package com.epam.jms_testing.dto;

import com.epam.jms_testing.dao.EventEntity;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(namespace = "net.javaguides.javaxmlparser.jaxb")
public class EventsInit {

	private List <EventDto> eventDtoList;

	@XmlElementWrapper(name = "eventList")
	@XmlElement(name = "event")
	public List<EventDto> getEventList() {
		return eventDtoList;
	}

	public void setEventList(List<EventDto> eventDtoList) {
		this.eventDtoList = eventDtoList;
	}

	private static final String EVENTS_XML = "src/main/resources/events-init.xml";

	public static List<EventEntity> convertXMLToEvents() {
		return convertXMLToEvents(EVENTS_XML);
	}

	public static List<EventEntity> convertXMLToEvents(String fileUri) {
		try {
			JAXBContext context = JAXBContext.newInstance(EventsInit.class);
			Unmarshaller un = context.createUnmarshaller();
			EventsInit eventsInit = (EventsInit) un.unmarshal(new File(fileUri));
			List<EventDto> eventDtos = eventsInit.getEventList();
			List<EventEntity> eventEntities = new ArrayList<>();
			for (EventDto eventDto : eventDtos) {
				EventEntity eventEntity = eventDto.toEntity();
				eventEntities.add(eventEntity);
			}
			return eventEntities;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}
}