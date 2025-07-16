package com.example.projetobrecho20.model;

public class BagWithCustomer {
    private final Bag bag;
    private final Customer customer;

    public BagWithCustomer(Bag bag, Customer customer) {
        this.bag = bag;
        this.customer = customer;
    }

    public Bag getBag() {
        return bag;
    }

    public Customer getCustomer() {
        return customer;
    }
}

