package com.example.aviasales2.service.impl;

import com.example.aviasales2.entity.Reservation;
import com.example.aviasales2.entity.User;
import com.example.aviasales2.repository.ReservationRepository;
import com.example.aviasales2.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    final
    ReservationRepository reservationRepository;

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
}
