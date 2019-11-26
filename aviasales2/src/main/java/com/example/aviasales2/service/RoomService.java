package com.example.aviasales2.service;

import com.example.aviasales2.entity.Room;

import java.util.List;

public interface RoomService {
    Room save(Room room);
    Room findByRoomId(long id);
    List<Room> findAll();
    void delete(Room room);
    void deleteById(Long id);
    void update(Room room);
    List<Room> getFreeRooms();
    List<Room> findRoomsByPriceAscending();
    List<Room> findRoomsByPriceDescending();
    List<Room> findRoomsByPriceInRange(double minPrice, double maxPrice);
    //List<Room> findRoomsByRoomConveniences(List<String> roomConvenience);
}
