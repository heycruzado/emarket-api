package com.alex.emarket_api.controller;

import com.alex.emarket_api.dto.TicketDTO;
import com.alex.emarket_api.entity.Client;
import com.alex.emarket_api.entity.Ticket;
import com.alex.emarket_api.service.TicketProductService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketProductService service;
    private final ModelMapper mapper;

    public TicketController(TicketProductService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<TicketDTO>> getClients(){
        List<TicketDTO> list = service.getAllTickets().stream()
                .map(this::convertToDTO)
                .toList();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getClientById(@PathVariable("id") Long id){
        TicketDTO ticketDTO = convertToDTO(service.getTicketById(id));
        return ResponseEntity.ok(ticketDTO);
    }

    @PostMapping
    public ResponseEntity<TicketDTO> saveClient(@RequestBody Ticket ticket){
        TicketDTO ticketDTO = convertToDTO(service.saveTicket(ticket));
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDTO> updateClient(@PathVariable("id") Long id, @RequestBody Ticket ticket){
        TicketDTO ticketDTO = convertToDTO(service.saveTicket(ticket));
        return ResponseEntity.ok().body(ticketDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") Long id){
        service.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }

    private TicketDTO convertToDTO(Ticket ticket){
        return mapper.map(ticket, TicketDTO.class);
    }

    private Ticket convertoToTicket(TicketDTO ticketDTO){
        return mapper.map(ticketDTO, Ticket.class);
    }


}
