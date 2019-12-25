package com.example.aviasales2.service.impl;

import com.example.aviasales2.PersonRequest;
import com.example.aviasales2.entity.Ticket;
import com.example.aviasales2.entity.Trip;
import com.example.aviasales2.entity.User;
import com.example.aviasales2.repository.TicketRepository;
import com.example.aviasales2.repository.TripRepository;
import com.example.aviasales2.repository.UserRepository;
import com.example.aviasales2.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final TripRepository tripRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, UserRepository userRepository, TripRepository tripRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.tripRepository = tripRepository;
    }

    @Override
    public Ticket findByTicketId(Long id) {
        return ticketRepository.findByTicketId(id);
    }

    @Override
    public List <Ticket> findAllByBuyer(User buyer) {
        return ticketRepository.findAllByBuyer(buyer);
    }

    @Override
    @Transactional
    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Transactional
    public String save(Long userId, Long tripId, int count, List <PersonRequest> passangers) {
        Trip trip = tripRepository.findByTripId(tripId);
        String listt = ("");

        String newLine = System.getProperty("line.separator");
        int fcs = trip.getFullCountSeats();
        for (int i = 0; i < count; i++) {
            String name = passangers.get(i).getFirstName();
            String lastname = passangers.get(i).getLastName();
            listt = listt + (i + 1 + ". " + lastname + " " + name + newLine);
            Ticket ticket = new Ticket(name, lastname, tripId, trip.getDateFrom(), trip.getCityFrom(), trip.getCityDest(), trip.getPrice(), userRepository.findByUserId(userId));
            ticketRepository.save(ticket);
            fcs--;
        }
        trip.setFullCountSeats(fcs);
        tripRepository.save(trip);

        return listt;
    }

    @Override
    public BigDecimal getSum(long id) {
        Ticket ticket = ticketRepository.findByTicketId(id);
        BigDecimal price = ticket.getPrice();
        return price;
    }

    @Override
    public void deleteById(Long ticketId) {
        ticketRepository.deleteById(ticketId);
    }

    @Override
    public void delete(Ticket ticket) {
        ticketRepository.delete(ticket);
    }

}
