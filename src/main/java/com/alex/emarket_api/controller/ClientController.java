package com.alex.emarket_api.controller;

import com.alex.emarket_api.dto.ClientDTO;
import com.alex.emarket_api.entity.Client;
import com.alex.emarket_api.service.IClientService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final IClientService service;
    private final ModelMapper mapper;

    public ClientController(IClientService IClientService, ModelMapper mapper) {
        this.service = IClientService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getClients() throws Exception {
        List<ClientDTO> list = service.getAllClients()
                .stream()
                .map(this::convertToDTO)
                //.map(client -> mapper.map(client, ClientDTO.class))
                .toList();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable("id") Long id) throws Exception {
        ClientDTO obj = convertToDTO(service.getClientById(id));
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> saveClient(@RequestBody ClientDTO clientDTO) throws Exception {
        Client client = service.saveClient(convertToEntity(clientDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(client));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable("id") Long id, @RequestBody ClientDTO clientDTO) throws Exception {
        Client client = service.updateClient(id, convertToEntity(clientDTO));
        return ResponseEntity.ok().body(convertToDTO(client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") Long id) throws Exception {
        service.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    private ClientDTO convertToDTO(Client client){
        return mapper.map(client, ClientDTO.class);
    }

    private Client convertToEntity(ClientDTO dto){
        return mapper.map(dto, Client.class);
    }
}
