package com.example.aviasales2.Repository;



import com.example.aviasales2.Entity.Transport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface TransportRepository extends CrudRepository<Transport,Long> {
    Optional<Transport> findById(Long id);
    List<Transport> findAll();
    Transport deleteAllByName(String name);
}
