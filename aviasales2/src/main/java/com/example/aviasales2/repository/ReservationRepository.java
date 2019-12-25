package com.example.aviasales2.repository;

import com.example.aviasales2.entity.Reservation;
import com.example.aviasales2.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository <Reservation, Long> {
    List <Reservation> findAllByBuyer(User buyer);

    Reservation findByReservationId(Long id);

    List <Reservation> findAllByRoomId(Long roomId);
}
