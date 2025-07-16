package com.example.projetobrecho20.model;

import java.util.List;

public class BagWithDetails {
    private final Bag bag;
    private final Customer customer;

    private final List<BagItem> bagItem;
    private final List<Product> productList;

    public BagWithDetails(Bag bag, Customer customer, List<BagItem> bagItem, List<Product> productList) {
        this.bag = bag;
        this.customer = customer;
        this.bagItem = bagItem;
        this.productList = productList;
    }

    public Bag getBag() {
        return bag;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<BagItem> getBagItem() {
        return bagItem;
    }

    public List<Product> getProductList() {
        return productList;
    }
}
