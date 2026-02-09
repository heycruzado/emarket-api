package com.alex.emarket_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ClientDTO {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long idClient;
    private String nameClient;

    public ClientDTO() {
    }

    public ClientDTO(Long idClient, String nameClient) {
        this.idClient = idClient;
        this.nameClient = nameClient;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "idClient=" + idClient +
                ", nameClient='" + nameClient + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ClientDTO clientDTO = (ClientDTO) o;
        return Objects.equals(idClient, clientDTO.idClient) && Objects.equals(nameClient, clientDTO.nameClient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClient, nameClient);
    }
}
