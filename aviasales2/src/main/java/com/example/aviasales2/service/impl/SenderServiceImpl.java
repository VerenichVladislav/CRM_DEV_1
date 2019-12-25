package com.example.aviasales2.service.impl;

import com.example.aviasales2.entity.City;
import com.example.aviasales2.entity.Sender;
import com.example.aviasales2.entity.Trip;
import com.example.aviasales2.entity.User;
import com.example.aviasales2.repository.TripRepository;
import com.example.aviasales2.repository.UserRepository;
import com.example.aviasales2.service.SenderService;
import com.example.aviasales2.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class SenderServiceImpl implements SenderService {
    private final TripRepository tripRepository;
    private final UserRepository userRepository;
    private final TripService tripService;

    @Autowired
    public SenderServiceImpl(TripRepository tripRepository, UserRepository userRepository, TripService tripService) {
        this.tripRepository = tripRepository;
        this.userRepository = userRepository;
        this.tripService = tripService;
    }

    @Override
    public void buyEmail(long userId, long tripId, String list, int count) {
        Trip trip = tripRepository.findByTripId(tripId);
        User buyer = userRepository.findByUserId(userId);
        City cityFrom = trip.getCityFrom();
        City cityDest = trip.getCityDest();
        String date = new SimpleDateFormat("HH:mm MM/dd/yyyy").format(trip.getDateFrom());
        StringBuffer sb = new StringBuffer();
        sb.append("Hello, " + buyer.getUserName() + "!\n\n");
        sb.append("You bought " + count + " ticket(s) from " + cityFrom.getCityName() +
                " to " + cityDest.getCityName() + "!\n\n");
        sb.append("Date: " + date + "\n");
        sb.append("Total Price: " + tripService.calculateCost(count, tripId) + "$\n\n");
        sb.append("Passengers: \n");
        sb.append(list + "\n");
        sb.append("Gracios!");
        String eml = buyer.getEmail();
        String text = sb.toString();
        Sender sender = new Sender();
        sender.send("Purchase information from AviaSales2.0", text, eml);
    }
    @Override
    public void buyEmailTour(long userId) {

        User buyer = userRepository.findByUserId(userId);

        StringBuffer sb = new StringBuffer();
        sb.append("Hello, " + buyer.getUserName() + "!\n\n");
        sb.append("You bouth tour");
        sb.append("Gracios!");
        String eml = buyer.getEmail();
        String text = sb.toString();
        Sender sender = new Sender();
        sender.send("Purchase information from AviaSales2.0", text, eml);
    }
}
