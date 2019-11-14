package com.example.aviasales2.controller;

import com.example.aviasales2.entity.Ticket;
import com.example.aviasales2.entity.transferObjects.TicketDTO;
import com.example.aviasales2.service.WalletService;
import com.example.aviasales2.service.TicketService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class TicketController {
    @Autowired
    TicketService ticketService;
    @Autowired
    WalletService walletService;
    @Autowired
    private DozerBeanMapper mapper;
    @PostMapping()
    public Ticket save(@RequestBody Ticket ticket) {
        return ticketService.save(ticket);
    }

    @GetMapping("/{id}")
    public TicketDTO findById(@PathVariable("id") long id) {
        return mapper.map(ticketService.findById(id), TicketDTO.class);
    }

    @GetMapping("/{user_id}/delete")
    ResponseEntity<String> deleteTicket(@PathVariable("user_id") long userId,
                                        @RequestParam long ticketId){
        BigDecimal price = ticketService.getSum(ticketId);
        walletService.backMoney(price,userId,ticketId);
        ticketService.deleteById(ticketId);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Successful! You delete this ticket!");
    }
}
