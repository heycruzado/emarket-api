package com.alex.emarket_api.controller;

import com.alex.emarket_api.entity.Client;
import com.alex.emarket_api.entity.Ticket;
import com.alex.emarket_api.service.TicketProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketProductService service;

    public TicketController(TicketProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getClients(){
        return ResponseEntity.ok(service.getAllTickets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getClientById(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.getTicketById(id));
    }

    @PostMapping
    public ResponseEntity<Ticket> saveClient(@RequestBody Ticket ticket){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveTicket(ticket));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateClient(@PathVariable("id") Long id, @RequestBody Ticket ticket){
        return ResponseEntity.ok(service.updateTicket(id, ticket));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") Long id){
        service.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }


}
