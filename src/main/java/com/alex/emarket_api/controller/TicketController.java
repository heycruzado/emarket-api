package com.alex.emarket_api.controller;

import com.alex.emarket_api.dto.TicketDTO;
import com.alex.emarket_api.entity.Ticket;
import com.alex.emarket_api.service.ITicketProductService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final ITicketProductService service;
    private final ModelMapper mapper;

    public TicketController(ITicketProductService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<TicketDTO>> getClients() throws Exception {
        List<TicketDTO> list = service.getAllTickets().stream()
                .map(this::convertToDTO)
                .toList();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getClientById(@PathVariable("id") Long id) throws Exception {
        TicketDTO ticketDTO = convertToDTO(service.getTicketById(id));
        return ResponseEntity.ok(ticketDTO);
    }

    @PostMapping
    public ResponseEntity<TicketDTO> saveClient(@RequestBody TicketDTO ticketDTO) throws Exception {
        Ticket ticket = service.saveTicket(convertToToEntity(ticketDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(ticket));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDTO> updateClient(@PathVariable("id") Long id, @RequestBody TicketDTO ticketDTO) throws Exception {
        Ticket ticket = service.updateTicket(id, convertToToEntity(ticketDTO));
        return ResponseEntity.ok().body(convertToDTO(ticket));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") Long id) throws Exception {
        service.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }

    private TicketDTO convertToDTO(Ticket ticket){
        return mapper.map(ticket, TicketDTO.class);
    }

    private Ticket convertToToEntity(TicketDTO ticketDTO){
        return mapper.map(ticketDTO, Ticket.class);
    }


}
