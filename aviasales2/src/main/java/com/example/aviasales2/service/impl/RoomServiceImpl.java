package com.example.aviasales2.service.impl;


import com.example.aviasales2.config.filterConfig.RoomFilter;
import com.example.aviasales2.entity.QRoom;
import com.example.aviasales2.entity.Room;
import com.example.aviasales2.repository.RoomRepository;
import com.example.aviasales2.service.RoomService;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    @PersistenceContext
    private EntityManager entityManager;


    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room findByRoomId(long id) {
        return roomRepository.findByRoomId(id);
    }

    @Override
    public List <Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public List <Room> findAll(RoomFilter roomFilter, int page, int pageSize, String order) {
        Sort sort = new Sort(Sort.Direction.ASC, "roomId");
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        final QRoom qRoom = QRoom.room;
        JPAQuery <Room> roomQuery = new JPAQuery <>(entityManager);
        roomQuery.from(qRoom).where(qRoom.isNotNull()
                .and(qRoom.status.eq("ok"))
                .and(qRoom.dailyCost.goe(roomFilter.getMinPrice())
                        .and(qRoom.dailyCost.loe(roomFilter.getMaxPrice()))));
//        if (!roomFilter.getRoomConveniences().isEmpty()) {
//            roomQuery.
//        }
        if (order.equals("desc")) {
            roomQuery.orderBy(qRoom.dailyCost.desc());
        } else {
            roomQuery.orderBy(qRoom.dailyCost.asc());
        }

        long total = roomQuery.fetchCount();

        roomQuery.offset(pageable.getOffset());
        roomQuery.limit(pageable.getPageSize());

        List <Room> content = total > pageable.getOffset() ? roomQuery.fetch() : Collections.emptyList();

        Page <Room> roomsPage = new PageImpl <>(content, pageable, total);
        return roomsPage.getContent();
    }


    @Override
    public void delete(Room room) {
        roomRepository.delete(room);
    }

    @Override
    public void deleteById(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public void update(Room room) {
        roomRepository.save(room);
    }

    @Override
    public List <Room> getFreeRooms() {
        return roomRepository.findAll().stream()
                .filter(room -> !room.getStatus().equals("busy"))
                .collect(Collectors.toList());
    }

    @Override
    public List <Room> findRoomsByRoomConveniences(List <String> roomConveniences) {
        return getFreeRooms().stream().filter(room1 -> room1.getRoomConvenience().stream().map(Enum::name).collect(Collectors.toList())
                .containsAll(roomConveniences))
                .collect(Collectors.toList());
    }
}
