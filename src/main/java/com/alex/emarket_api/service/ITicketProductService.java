package com.alex.emarket_api.service;

import com.alex.emarket_api.entity.Ticket;

import java.util.List;

public interface ITicketProductService {

    List<Ticket> getAllTickets() throws Exception;
    Ticket getTicketById(Long id) throws Exception;
    Ticket saveTicket(Ticket client) throws Exception;
    Ticket updateTicket(Long id, Ticket ticket) throws Exception;
    void deleteTicket(Long id) throws Exception;

}
