package com.epam.jms_testing.repository;


import com.epam.jms_testing.dao.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
}
