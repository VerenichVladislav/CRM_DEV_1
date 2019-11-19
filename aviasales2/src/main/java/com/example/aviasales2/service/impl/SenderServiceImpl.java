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

@Service
public class SenderServiceImpl implements SenderService {
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TripService tripService;

    @Override
    public void buyEmail(long userId, long tripId, String list, int count) {
        Trip trip = tripRepository.findByTripId(tripId);
        User buyer = userRepository.findByUserId(userId);
        City cityFrom = trip.getCityFrom();
        City cityDest = trip.getCityDest();
        StringBuffer sb = new StringBuffer();
        sb.append("Hello, " + buyer.getUserName() + "!\n\n");
        sb.append("You bought " + count + " ticket(s) from " + cityFrom.getCityName() +
                " to " + cityDest.getCityName() + "!\n\n");
        sb.append("Date: " + trip.getDateFrom()+"\n");
        //sb.append("Total Price: " + trip.getPrice() * count + "$\n\n");
        sb.append("Total Price: " + tripService.calculateCost(count, tripId) + "$\n\n");
        sb.append("Passengers: \n");
        sb.append(list+"\n");
        sb.append("Gracios!");
        String eml = buyer.getEmail();
        String text = sb.toString();
        Sender sender = new Sender();
        sender.send("Purchase information from AviaSales2.0", text, eml);
    }
}
