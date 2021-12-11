package com.epam.jms_testing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;

@EnableJms
@SpringBootApplication
public class JmsTestingApplication {
	private static final Logger log = LoggerFactory.getLogger(JmsTestingApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(JmsTestingApplication.class, args);
	}

	@Bean
	public JmsListenerContainerFactory<?> myFactory(
			ConnectionFactory connectionFactory,
			DefaultJmsListenerContainerFactoryConfigurer configurer
	) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();

		factory.setErrorHandler(t -> log.warn("An error has occurred in the transaction", t));

		configurer.configure(factory, connectionFactory);
		return factory;
	}

	// Serialize message content to json using TextMessage
	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

}
