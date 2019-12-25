package com.example.aviasales2.controller;

import com.example.aviasales2.config.filterConfig.RoomFilter;
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

    private final RoomServiceImpl roomService;
    private final DozerBeanMapper mapper;

    @Autowired
    public RoomController(RoomServiceImpl roomService, DozerBeanMapper mapper) {
        this.roomService = roomService;
        this.mapper = mapper;
    }


    @PostMapping("/all")
    public List <RoomDTO> findAll(@RequestBody RoomFilter roomFilter,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int pageSize,
                                  @RequestParam(defaultValue = "asc") String order) {
        return roomService.findAll(roomFilter, page, pageSize, order)
                .stream().map(entity -> mapper.map(entity, RoomDTO.class)).collect(Collectors.toList());
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
}
