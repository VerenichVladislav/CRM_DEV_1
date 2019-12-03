package com.example.aviasales2.config;

import com.example.aviasales2.entity.transferObjects.TransportDTO;
import org.dozer.DozerConverter;

import java.util.List;


//TEST CLASS!!!!!!!!!!!!


public class CustomConverter extends DozerConverter <List, TransportDTO> {


    public CustomConverter() {
        super(List.class, TransportDTO.class);
    }

    @Override
    public TransportDTO convertTo(List list, TransportDTO transportDTO) {
        return null;
    }

    @Override
    public List convertFrom(TransportDTO transportDTO, List list) {
        return null;
    }


}