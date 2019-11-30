package com.example.aviasales2.controller;

import com.example.aviasales2.entity.Room;
import com.example.aviasales2.entity.transferObjects.RoomDTO;
import com.example.aviasales2.service.impl.RoomServiceImpl;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 10000)
@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomServiceImpl roomService;
    @Autowired
    private DozerBeanMapper mapper;


    @GetMapping
    public List<RoomDTO> findAll() {
        return roomService.findAll().stream().map(entity -> mapper.map(entity, RoomDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public RoomDTO findById(@PathVariable("id") Long id) {
        return mapper.map(roomService.findByRoomId(id), RoomDTO.class);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        roomService.deleteById(id);
    }

    @DeleteMapping
    public void delete(@RequestBody Room room) {
        roomService.delete(room);
    }

    @PostMapping
    public Room save(@RequestBody Room room) {
        return roomService.save(room);
    }

    @PutMapping
    public void update(@RequestBody Room newCity) {
        roomService.update(newCity);
    }

    @GetMapping("/freeRooms")
    public List<RoomDTO> getFreeRooms() {
        List<Room> rooms = roomService.getFreeRooms();
        return rooms.stream().map(entity -> mapper.map(entity, RoomDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/priceSortAscending")
    public List<RoomDTO> findRoomsByPriceAscending() {
        List<Room> sortedRooms = roomService.findRoomsByPriceAscending();
        return sortedRooms.stream().map(entity -> mapper.map(entity, RoomDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/priceSortDescending")
    public List<RoomDTO> findRoomsByPriceDescending() {
        List<Room> sortedRooms = roomService.findRoomsByPriceDescending();
        return sortedRooms.stream().map(entity -> mapper.map(entity, RoomDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/byPriceInRange")
    public List<RoomDTO> findRoomsByPriceInRange(@RequestParam double minPrice, @RequestParam double maxPrice) {
        List<Room> sortedRooms = roomService.findRoomsByPriceInRange(minPrice, maxPrice);
        return sortedRooms.stream().map(entity -> mapper.map(entity, RoomDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/byRoomConveniences")
    public List<RoomDTO> findRoomsByRoomConveniences(@RequestParam List<String> roomConveniences){
        List<Room> goodRooms = roomService.findRoomsByRoomConveniences(roomConveniences);
        return goodRooms.stream().map(entity -> mapper.map(entity, RoomDTO.class)).collect(Collectors.toList());
    }
}
