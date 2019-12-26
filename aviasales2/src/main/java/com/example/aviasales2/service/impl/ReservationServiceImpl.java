package com.example.aviasales2.service.impl;

import com.example.aviasales2.entity.Reservation;
import com.example.aviasales2.entity.User;
import com.example.aviasales2.entity.transferObjects.ReservationDTO;
import com.example.aviasales2.repository.ReservationRepository;
import com.example.aviasales2.service.ReservationService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final DozerBeanMapper mapper;


    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, DozerBeanMapper mapper) {
        this.reservationRepository = reservationRepository;
        this.mapper = mapper;
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
    public List<LocalDate> findReservationDatesByRoomId(Long roomId) {


        List<LocalDate> totalDates = new ArrayList<>();
        List<ReservationDTO> reservations = reservationRepository.findAllByRoomId(roomId).stream()
                .map(entity -> mapper.map(entity, ReservationDTO.class))
                .collect(Collectors.toList());
        ;
        for (ReservationDTO reservation : reservations) {
            LocalDate start = reservation.getCheckIn().toLocalDateTime().toLocalDate();
            LocalDate end = reservation.getCheckOut().toLocalDateTime().toLocalDate();

            while (!start.isAfter(end)) {
                totalDates.add(start);
                start = start.plusDays(1);
            }
        }
        return totalDates;
    }
}
