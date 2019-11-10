package com.example.aviasales2.service;

import com.example.aviasales2.PersonRequest;
import com.example.aviasales2.entity.Ticket;
import java.util.List;

public interface TicketService {
    Ticket save(Ticket ticket);
    String save(long userId, long tripId, int count, List<PersonRequest> passangers);
    void delete(Ticket ticket);
    void deleteById(long ticketId);
    Ticket findById(long id);
    Double getSum(long id);
}
