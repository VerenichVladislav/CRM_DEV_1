package com.example.aviasales2.service.impl;

import com.example.aviasales2.entity.Reservation;
import com.example.aviasales2.entity.User;
import com.example.aviasales2.repository.ReservationRepository;
import com.example.aviasales2.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation findById(Long id) {
        return reservationRepository.findByReservationId(id);
    }

    @Override
    public List <Reservation> findAllByBuyer(User buyer) {
        return reservationRepository.findAllByBuyer(buyer);
    }

    @Override
    public List<Date> findReservationDatesByRoomId(Long roomId) {
        List<Reservation> reservations = reservationRepository.findAllByRoomId(roomId);
        List<Date> allReservedDates = new ArrayList<>();
        for (Reservation reservation : reservations) {
            Date result = new Date();
            allReservedDates.add(reservation.getCheckIn());
            while (!result.equals(reservation.getCheckOut())) {
                Date date = new Date(reservation.getCheckIn().getTime());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(Calendar.DATE, 1);
                Date datePlusOne = calendar.getTime();
                result = datePlusOne;
                allReservedDates.add(datePlusOne);
            }
        }
        return allReservedDates;
    }
}
