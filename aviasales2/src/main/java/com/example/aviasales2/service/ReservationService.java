package com.example.aviasales2.service;

import com.example.aviasales2.entity.Reservation;
import com.example.aviasales2.entity.User;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ReservationService {
    Reservation findById(Long id);

    List<Reservation> findAllByBuyer(User buyer);

    List<LocalDate> findReservationDatesByRoomId(Long roomId);
}
