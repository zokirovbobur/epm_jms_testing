package com.epam.jms_testing.repository;

import com.epam.jms_testing.entity.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, String> {}
