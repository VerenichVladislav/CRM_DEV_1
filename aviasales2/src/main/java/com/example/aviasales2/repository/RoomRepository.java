package com.example.aviasales2.repository;

import com.example.aviasales2.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
    Room findByRoomId(long id);
    List<Room> findAll();
}
