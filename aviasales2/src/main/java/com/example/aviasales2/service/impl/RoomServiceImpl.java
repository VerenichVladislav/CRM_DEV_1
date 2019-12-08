package com.example.aviasales2.service.impl;


import com.example.aviasales2.entity.Room;
import com.example.aviasales2.repository.RoomRepository;
import com.example.aviasales2.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

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
    public List <Room> findRoomsByPriceAscending() {
        return getFreeRooms().stream().
                sorted(Comparator.comparingDouble(Room::getDailyCost)).
                collect(Collectors.toList());
    }

    @Override
    public List <Room> findRoomsByPriceDescending() {
        return getFreeRooms().stream().
                sorted(Comparator.comparingDouble(Room::getDailyCost).reversed()).
                collect(Collectors.toList());
    }

    @Override
    public List <Room> findRoomsByPriceInRange(double minPrice, double maxPrice) {
        return getFreeRooms().stream()
                .filter(room -> room.getDailyCost() >= minPrice &&
                        room.getDailyCost() <= maxPrice)
                .collect(Collectors.toList());
    }

    @Override
    public List <Room> findRoomsByRoomConveniences(List <String> roomConveniences) {
        return getFreeRooms().stream().filter(room1 -> room1.getRoomConvenience().stream().map(Enum::name).collect(Collectors.toList())
                .containsAll(roomConveniences))
                .collect(Collectors.toList());
    }
}
