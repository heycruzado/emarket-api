package com.alex.emarket_api.service.impl;

import com.alex.emarket_api.dto.TicketDTO;
import com.alex.emarket_api.entity.Product;
import com.alex.emarket_api.entity.Ticket;
import com.alex.emarket_api.exception.ModelNotFoundException;
import com.alex.emarket_api.repository.TicketRepository;
import com.alex.emarket_api.service.TicketProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketProductService {

    private final TicketRepository repository;

    public TicketServiceImpl(TicketRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Ticket> getAllTickets() {
        return repository.findAll();
    }

    @Override
    public Ticket getTicketById(Long id) {
        List<Ticket> findAll = repository.findAll();
        return findAll.stream().filter(product -> product.getIdTicket().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    @Override
    public Ticket saveTicket(Ticket ticket) {
        return repository.save(ticket);
    }

    @Override
    public Ticket updateTicket(Long id, Ticket ticket) throws ModelNotFoundException {
        Ticket ticketSelected = repository.findById(id).orElseThrow(() -> new ModelNotFoundException("Id not found" + id));
        ticketSelected.setClient(ticket.getClient());
        ticketSelected.setProducts(ticket.getProducts());
        return repository.save(ticketSelected);
    }

    @Override
    public void deleteTicket(Long id) throws ModelNotFoundException {
        repository.findById(id).orElseThrow(() -> new ModelNotFoundException("Id not found"+id));
        repository.deleteById(id);
    }

}
