package com.alex.emarket_api.service.impl;

import com.alex.emarket_api.entity.Client;
import com.alex.emarket_api.repository.ClientRepository;
import com.alex.emarket_api.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;

    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Client> getAllClients() {
        return repository.findAll();
    }

    @Override
    public Client getClientById(Long id) {
        List<Client> findAll = repository.findAll();
        return findAll.stream().filter(client -> client.getIdClient().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }

    @Override
    public Client saveClient(Client client) {
        Optional<Client> existing = repository.findByName(client.getName());
        if(existing.isPresent()){
            throw new RuntimeException("Client already exists in the database");
        }
        return repository.save(client);
    }

    @Override
    public Client updateClient(Long id, Client client) {
        Client clientSelected = repository.findById(id).orElseThrow(() -> new RuntimeException("Id not found" + id));
        clientSelected.setName(client.getName());
        return repository.save(clientSelected);
    }

    @Override
    public void deleteClient(Long id) {
        repository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"+id));
        repository.deleteById(id);
    }
}
