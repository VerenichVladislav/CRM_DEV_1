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
import java.util.*;
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
    public List<Room> findAll(RoomFilter roomFilter, int page, int pageSize, String order) {
        Sort sort = new Sort(Sort.Direction.ASC, "roomId");
        Pageable pageable = PageRequest.of(page, pageSize, sort);

        JPAQuery<Room> roomQuery = roomDatabaseFilter(roomFilter, order);

        List<Room> rooms = roomQuery
                .fetch();
        Set<Room> result = new HashSet<>(rooms);
        rooms = new ArrayList<>(result);
        rooms.sort((roomsFirst, roomsSorted) ->
                (int) (roomsFirst.getRoomId() - roomsSorted.getRoomId()));

        long total = roomQuery.fetchCount();
        roomQuery.offset(pageable.getOffset());
        roomQuery.limit(pageable.getPageSize());

        List<Room> content = total > pageable.getOffset() ? rooms : Collections.emptyList();

        if (!roomFilter.getRoomConveniences().isEmpty()) {
            content = content.stream().filter(room -> room.getRoomConvenience().stream().map(Enum::name).collect(Collectors.toList())
                    .containsAll(roomFilter.getRoomConveniences()))
                    .collect(Collectors.toList());
        }

        Page<Room> roomsPage = new PageImpl<>(content, pageable, total);

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

    private JPAQuery<Room> roomDatabaseFilter(RoomFilter roomFilter, String order){
        final QRoom qRoom = QRoom.room;
        JPAQuery<Room> roomQuery = new JPAQuery<>(entityManager);
        roomQuery.from(qRoom).where(qRoom.isNotNull()
                .and(qRoom.status.eq("ok"))
                .and(qRoom.dailyCost.goe(roomFilter.getMinPrice())
                        .and(qRoom.dailyCost.loe(roomFilter.getMaxPrice()))));
        if (order.equals("desc")) {
            roomQuery.orderBy(qRoom.dailyCost.desc());
        } else {
            roomQuery.orderBy(qRoom.dailyCost.asc());
        }
        return roomQuery;
    }
}
