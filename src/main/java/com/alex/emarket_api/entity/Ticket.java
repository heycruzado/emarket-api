package com.alex.emarket_api.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTicket;

    @ManyToOne
    @JoinColumn(name = "idClient")
    private Client client;

    @ManyToMany
    @JoinTable(
            name = "ticket_product",
            joinColumns = @JoinColumn(name = "idTicket"),
            inverseJoinColumns = @JoinColumn(name= "idProduct")

    )
    private List<Product> products;

    public Ticket() {
    }

    public Ticket(Long idTicket, Client client, List<Product> products) {
        this.idTicket = idTicket;
        this.client = client;
        this.products = products;
    }

    public Long getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Long idTicket) {
        this.idTicket = idTicket;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "idTicket=" + idTicket +
                ", client=" + client +
                ", product=" + products +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(idTicket, ticket.idTicket) && Objects.equals(client, ticket.client) && Objects.equals(products, ticket.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTicket, client, products);
    }
}
