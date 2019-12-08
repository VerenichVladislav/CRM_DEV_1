package com.example.aviasales2.repository;

import com.example.aviasales2.entity.Ticket;
import com.example.aviasales2.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends CrudRepository <Ticket, Long> {
    Ticket findByTicketId(Long id);

    List <Ticket> findAllByBuyer(User Buyer);
}
