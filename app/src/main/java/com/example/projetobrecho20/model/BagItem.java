package com.example.projetobrecho20.model;
public class BagItem {
    private String id;
    private String productId;
    private int quantity;
    private String bagId;

    public BagItem(String id, String productId, int quantity, String bagId) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.bagId = bagId;
    }

    public String getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getBagId() {
        return bagId;
    }
}

