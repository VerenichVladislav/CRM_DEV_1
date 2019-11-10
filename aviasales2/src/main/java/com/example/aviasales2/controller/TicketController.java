package com.example.aviasales2.controller;

import com.example.aviasales2.entity.Ticket;
import com.example.aviasales2.service.IWalletService;
import com.example.aviasales2.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TicketController {
    @Autowired
    TicketService ticketService;
    @Autowired
    IWalletService walletService;
    @PostMapping()
    public Ticket save(@RequestBody Ticket ticket) {
        return ticketService.save(ticket);
    }

    @GetMapping("/{id}")
    public Ticket findById(@PathVariable("id") long id) {
        return ticketService.findById(id);
    }

    @GetMapping("/{user_id}/delete")
    ResponseEntity<String> deleteTicket(@PathVariable("user_id") long userId,
                                        @RequestParam long ticketId){
        Double price = ticketService.getSum(ticketId);
        walletService.backMoney(price,userId,ticketId);
        ticketService.deleteById(ticketId);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Successful! You delete this ticket!");
    }
}
