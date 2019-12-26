package com.example.aviasales2.service.impl;

import com.example.aviasales2.entity.City;
import com.example.aviasales2.entity.Sender;
import com.example.aviasales2.entity.Trip;
import com.example.aviasales2.entity.User;
import com.example.aviasales2.repository.TripRepository;
import com.example.aviasales2.repository.UserRepository;
import com.example.aviasales2.service.SenderService;
import com.example.aviasales2.service.TripService;
import com.example.aviasales2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class SenderServiceImpl implements SenderService {
    private final UserService userService;
    private final TripService tripService;

    @Autowired
    public SenderServiceImpl(UserService userService, TripService tripService) {
        this.userService = userService;
        this.tripService = tripService;
    }

    @Override
    public void buyEmail(Long userId, Long tripId, String list, int count) {
        Trip trip = tripService.findById(tripId);
        User buyer = userService.findById(userId);
        String cityFrom = trip.getCityFrom().getCityName();
        String cityDest = trip.getCityDest().getCityName();
        String date = new SimpleDateFormat("HH:mm MM/dd/yyyy").format(trip.getDateFrom());
        sendEmail(tripId, list, count, buyer, cityFrom, cityDest, date);
    }

    private void sendEmail(Long tripId, String list, int count, User buyer, String cityFrom, String cityDest, String date) {
        StringBuffer sb = new StringBuffer();
        sb.append("Hello, " + buyer.getUserName() + "!\n\n");
        sb.append("You bought " + count + " ticket(s) from " + cityFrom +
                " to " + cityDest + "!\n\n");
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
    public void buyEmailTour(Long userId) {

        User buyer = userService.findById(userId);

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
