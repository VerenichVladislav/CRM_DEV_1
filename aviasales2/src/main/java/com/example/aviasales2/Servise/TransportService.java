package com.example.aviasales2.Servise;





import com.example.aviasales2.Entity.Transport;

import java.util.List;
import java.util.Optional;

public interface TransportService {
    Transport save(Transport transport);
    Optional<Transport> findById(Long id);
    List<Transport> findAll();
    void update(Transport transport);
    void deleteById(Long id);
}
