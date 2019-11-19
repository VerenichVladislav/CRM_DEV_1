package com.example.aviasales2.service.impl;


import com.example.aviasales2.entity.Room;
import com.example.aviasales2.repository.RoomRepository;
import com.example.aviasales2.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomRepository roomRepository;


    @Override
    public Room findByRoomId(long id) {
       return roomRepository.findByRoomId(id);
    }
}
