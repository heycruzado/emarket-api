package com.alex.emarket_api.service.impl;

import com.alex.emarket_api.entity.Client;
import com.alex.emarket_api.exception.ModelNotFoundException;
import com.alex.emarket_api.repository.ClientRepository;
import com.alex.emarket_api.service.IClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IClientServiceImpl implements IClientService {

    private final ClientRepository repository;

    public IClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Client> getAllClients() throws Exception {
        return repository.findAll();
    }

    @Override
    public Client getClientById(Long id) throws Exception {
        List<Client> findAll = repository.findAll();
        return findAll.stream().filter(client -> client.getIdClient().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }

    @Override
    public Client saveClient(Client client) throws Exception{
        Optional<Client> existing = repository.findByName(client.getName());
        if(existing.isPresent()){
            throw new RuntimeException("Client already exists in the database");
        }
        return repository.save(client);
    }

    @Override
    public Client updateClient(Long id, Client client) throws Exception {
        Client clientSelected = repository.findById(id).orElseThrow(() -> new ModelNotFoundException("Id not found" + id));
        clientSelected.setName(client.getName());
        return repository.save(clientSelected);
    }

    @Override
    public void deleteClient(Long id) throws Exception {
        repository.findById(id).orElseThrow(() -> new ModelNotFoundException("Id not found"+id));
        repository.deleteById(id);
    }
}
