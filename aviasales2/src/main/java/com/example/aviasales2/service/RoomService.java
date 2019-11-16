package com.example.aviasales2.service;

import com.example.aviasales2.entity.Room;

import java.util.Optional;

public interface RoomService {
    Room findByRoomId(long id);
}
