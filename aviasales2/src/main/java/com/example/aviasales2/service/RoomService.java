package com.example.aviasales2.service;

import com.example.aviasales2.config.filterConfig.RoomFilter;
import com.example.aviasales2.entity.Room;

import java.util.List;

public interface RoomService {
    Room save(Room room);
    Room findByRoomId(long id);
    List<Room> findAll();
    List <Room> findAll(RoomFilter roomFilter, int page, int pageSize, String order);
    void delete(Room room);
    void deleteById(Long id);
    void update(Room room);
}
