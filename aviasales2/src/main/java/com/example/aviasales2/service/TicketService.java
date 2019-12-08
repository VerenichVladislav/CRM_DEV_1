package com.example.aviasales2.service;

import com.example.aviasales2.PersonRequest;
import com.example.aviasales2.entity.Ticket;
import com.example.aviasales2.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface TicketService {
    Ticket save(Ticket ticket);

    String save(Long userId, Long tripId, int count, List <PersonRequest> passangers);

    void delete(Ticket ticket);

    void deleteById(Long ticketId);

    Ticket findByTicketId(Long id);

    List <Ticket> findAllByBuyer(User buyer);

    BigDecimal getSum(long id);
}
