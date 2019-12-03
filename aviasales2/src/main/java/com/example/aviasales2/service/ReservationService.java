package com.example.aviasales2.service;

import com.example.aviasales2.entity.Reservation;
import com.example.aviasales2.entity.User;

import java.util.List;

public interface ReservationService {
    Reservation findById(Long id);

    List <Reservation> findAllByBuyer(User buyer);
}
