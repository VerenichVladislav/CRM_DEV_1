package com.example.aviasales2.repository;

import com.example.aviasales2.entity.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
    Ticket findById(long id);

}
