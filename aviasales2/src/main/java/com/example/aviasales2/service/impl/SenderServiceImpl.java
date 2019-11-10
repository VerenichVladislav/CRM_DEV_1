package com.example.aviasales2.service.impl;

import com.example.aviasales2.entity.City;
import com.example.aviasales2.entity.Sender;
import com.example.aviasales2.entity.Trip;
import com.example.aviasales2.entity.User;
import com.example.aviasales2.repository.TripRepository;
import com.example.aviasales2.repository.UserRepository;
import com.example.aviasales2.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SenderServiceImpl implements SenderService {
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void buyEmail(long userId, long tripId, String list, int count) {
        Trip trip = tripRepository.findById(tripId);
        User buyer = userRepository.findById(userId);
        City cityFrom = trip.getCityDest();
        City cityDest = trip.getCityFrom();
        StringBuffer sb = new StringBuffer();
        sb.append("Hello, " + buyer.getUserName() + "!\n\n");
        sb.append("You bought " + count + " ticket(s) from " + cityFrom.getCityName() +
                " to " + cityDest.getCityName() + "!\n\n");
        sb.append("Date: " + trip.getDateFrom()+"\n");
        sb.append("Total Price: " + trip.getPrice() * count + "$\n\n");
        sb.append("Passengers: \n");
        sb.append(list+"\n");
        sb.append("Gracios!");
        String eml = buyer.getEmail();
        String text = sb.toString();
        Sender sender = new Sender();
        sender.send("Purchase information from AviaSales2.0", text, eml);
    }
}
