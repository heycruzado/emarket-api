package com.alex.emarket_api.service;

import com.alex.emarket_api.entity.Client;

import java.util.List;

public interface IClientService {

    List<Client> getAllClients() throws Exception;
    Client getClientById(Long id) throws Exception;
    Client saveClient(Client client) throws Exception;
    Client updateClient(Long id, Client client) throws Exception;
    void deleteClient(Long id) throws Exception;

}
