package com.example.aviasales2.service.impl;


import com.example.aviasales2.entity.Room;
import com.example.aviasales2.entity.RoomConvenience;
import com.example.aviasales2.repository.RoomRepository;
import com.example.aviasales2.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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
        List <Room> rooms = getFreeRooms();
        List <Room> goodRooms = new ArrayList <>();

//        rooms.forEach(room -> room.getRoomConvenience().stream()
//                .filter(convenience -> roomConveniences.contains(convenience.name()))
//                .collect(Collectors.toList()).stream()
//                .filter(roomConvenience -> !goodRooms.contains(room))
//                .forEach(convenience -> goodRooms.add(room)));

        Map <Room, List <String>> enumString = new HashMap <>();
        for (Room room : rooms) {
            for (RoomConvenience roomConvenience : room.getRoomConvenience()) {
                if (roomConveniences.contains(roomConvenience.name())
                        && !goodRooms.contains(room)) {
                    goodRooms.add(room);
                }
            }
        }

        for (Room room : goodRooms) {
            List <String> tempName = new ArrayList <>();
            for (RoomConvenience roomConvenience : room.getRoomConvenience()) {
                String name = roomConvenience.name();
                tempName.add(name);
            }
            enumString.put(room, tempName);
        }

        goodRooms.removeIf(room -> !enumString.get(room).containsAll(roomConveniences));

        return goodRooms;
    }
}
