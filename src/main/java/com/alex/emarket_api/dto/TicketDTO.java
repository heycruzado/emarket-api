package com.alex.emarket_api.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class TicketDTO {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long idTicket;


    private ClientDTO clientTicket;

    private List<ProductDTO> productsTicket;

    public TicketDTO() {
    }

    public TicketDTO(Long idTicket, ClientDTO clientTicket, List<ProductDTO> productsTicket) {
        this.idTicket = idTicket;
        this.clientTicket = clientTicket;
        this.productsTicket = productsTicket;
    }

    public Long getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Long idTicket) {
        this.idTicket = idTicket;
    }

    public ClientDTO getClientTicket() {
        return clientTicket;
    }

    public void setClientTicket(ClientDTO clientTicket) {
        this.clientTicket = clientTicket;
    }

    public List<ProductDTO> getProductsTicket() {
        return productsTicket;
    }

    public void setProductsTicket(List<ProductDTO> productsTicket) {
        this.productsTicket = productsTicket;
    }

    @Override
    public String toString() {
        return "TicketDTO{" +
                "idTicket=" + idTicket +
                ", clientTicket=" + clientTicket +
                ", productsTicket=" + productsTicket +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TicketDTO ticketDTO = (TicketDTO) o;
        return Objects.equals(idTicket, ticketDTO.idTicket) && Objects.equals(clientTicket, ticketDTO.clientTicket) && Objects.equals(productsTicket, ticketDTO.productsTicket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTicket, clientTicket, productsTicket);
    }
}
