package com.alex.emarket_api.service;

import com.alex.emarket_api.entity.Client;

import java.util.List;

public interface ClientService {

    List<Client> getAllClients();
    Client getClientById(Long id);
    Client saveClient(Client client);
    Client updateClient(Long id, Client client);
    void deleteClient(Long id);

}
