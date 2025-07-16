package com.example.projetobrecho20.model;

public class Bag {
    private final String id;
    private final String customerId;
    private final Boolean isDelivered;
    private final String createdAt;
    private final String deliveredAt;

    public Bag(String id, String customerId, Boolean isDelivered, String createdAt, String deliveredAt) {
        this.id = id;
        this.customerId = customerId;
        this.isDelivered = isDelivered;
        this.createdAt = createdAt;
        this.deliveredAt = deliveredAt;

    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Boolean getDelivered() {
        return isDelivered;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getDeliveredAt() {
        return deliveredAt;
    }
}
