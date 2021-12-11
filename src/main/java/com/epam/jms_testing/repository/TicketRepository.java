package com.epam.jms_testing.repository;


import com.epam.jms_testing.dao.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
}
