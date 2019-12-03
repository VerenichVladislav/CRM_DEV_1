package com.example.aviasales2.controller;

import com.example.aviasales2.entity.Ticket;
import com.example.aviasales2.entity.User;
import com.example.aviasales2.entity.transferObjects.TicketDTO;
import com.example.aviasales2.service.TicketService;
import com.example.aviasales2.service.UserService;
import com.example.aviasales2.service.WalletService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 10000)
@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;
    private final WalletService walletService;
    private final UserService userService;
    private final DozerBeanMapper mapper;

    @Autowired
    public TicketController(TicketService ticketService, WalletService walletService, UserService userService, DozerBeanMapper mapper) {
        this.ticketService = ticketService;
        this.walletService = walletService;
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping()
    public String save(@RequestBody @Valid Ticket ticket, BindingResult result) {
        if (result.hasErrors()) {
            return String.valueOf(result.getFieldErrors());
        }
        ticketService.save(ticket);
        return "saved";
    }

    @GetMapping("/{id}")
    public TicketDTO findById(@PathVariable("id") long id) {
        return mapper.map(ticketService.findByTicketId(id), TicketDTO.class);
    }

    @GetMapping("/buyer/{buyer_id}")
    public List <TicketDTO> findAllByBuyerId(@PathVariable("buyer_id") Long id) {
        User user = userService.findById(id);
        return ticketService.findAllByBuyer(user).stream()
                .map(entity -> mapper.map(entity, TicketDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{user_id}/delete")
    ResponseEntity <String> deleteTicket(@PathVariable("user_id") long userId,
                                         @RequestParam long ticketId) {
        BigDecimal price = ticketService.getSum(ticketId);
        walletService.backMoney(price, userId, ticketId);
        ticketService.deleteById(ticketId);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Successful! You delete this ticket!");
    }
}
