package com.example.aviasales2.Controller;


import com.example.aviasales2.Entity.Transport;
import com.example.aviasales2.Servise.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequestMapping("/Transport")
@RestController
public class TransportController {
    @Autowired
    TransportService transportService;


    @GetMapping("/getAllTransport")
    private List<Transport> getAllTransport()
    {
        return transportService.findAll();
    }

    @GetMapping("/getTransport")
    private Optional<Transport> getTransport(@RequestParam(name = "id")Long id){
        return transportService.findById(id);
    }

    @PostMapping("/updateTransport")
    private void updateTransport(@RequestBody Transport transport)
    {
        transportService.update(transport);
    }



    @GetMapping("/deleteTransport")
    private void deleteTransport(@RequestParam(name = "id")Long id){
        transportService.deleteById(id);
    }


   @PostMapping("/save")
    private Transport save(@RequestBody Transport transport)
    {
        return transportService.save(transport);
    }




}
