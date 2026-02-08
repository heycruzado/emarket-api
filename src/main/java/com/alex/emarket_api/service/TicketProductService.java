package com.alex.emarket_api.service;



import com.alex.emarket_api.entity.Ticket;

import java.util.List;

public interface TicketProductService {

    List<Ticket> getAllTickets();
    Ticket getTicketById(Long id);
    Ticket saveTicket(Ticket client);
    Ticket updateTicket(Long id, Ticket client);
    void deleteTicket(Long id);

}
