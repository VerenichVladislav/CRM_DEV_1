package com.example.aviasales2.service.impl;

import com.example.aviasales2.PersonRequest;
import com.example.aviasales2.entity.Ticket;
import com.example.aviasales2.entity.Trip;
import com.example.aviasales2.repository.TicketRepository;
import com.example.aviasales2.repository.TripRepository;
import com.example.aviasales2.repository.UserRepository;
import com.example.aviasales2.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TripRepository tripRepository;

    @Override
    public Ticket findById(long id) {
        return ticketRepository.findById(id);
    }

    @Override
    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }


    public String save(long userId, long tripId, int count, List <PersonRequest> passangers) {
        Trip trip = tripRepository.findById(tripId);
        String listt = ("");
        String newLine = System.getProperty("line.separator");
        int fcs = trip.getFullCountSeats();
        for (int i = 0; i < count; i++) {
            String name = passangers.get(i).getName();
            String lastname = passangers.get(i).getLastName();
            listt = listt + (i + 1 + ". " + lastname + " " + name + newLine);
            Ticket ticket = new Ticket(name, lastname, tripId, trip.getDateFrom(), trip.getCityFrom().getId(), trip.getCityDest().getId(), trip.getPrice(), userRepository.findById(userId));
            ticketRepository.save(ticket);
            fcs--;
        }
        trip.setFullCountSeats(fcs);
        tripRepository.save(trip);
        return listt;
    }

    @Override
    public BigDecimal getSum(long id) {
        Ticket ticket = ticketRepository.findById(id);
        BigDecimal price = ticket.getPrice();
        return price;
    }

    @Override
    public void deleteById(long ticketId) {
        ticketRepository.deleteById(ticketId);
    }

    @Override
    public void delete(Ticket ticket) {
        ticketRepository.delete(ticket);
    }

}
