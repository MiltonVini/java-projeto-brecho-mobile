package com.example.projetobrecho20.model;

import java.math.BigDecimal;

public class Product {
    public enum StockType {
        UNIQUE,
        MULTIPLE
    }
    private String id;
    private String description;
    private BigDecimal price;
    private BigDecimal cost;
    private StockType stockType;
    private boolean isSold;
    private String soldAt;

    public Product(String id, String description, BigDecimal price, BigDecimal cost, StockType stockType, boolean isSold, String soldAt) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.cost = cost;
        this.stockType = stockType;
        this.isSold = isSold;
        this.soldAt = soldAt;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }


    public BigDecimal getCost() {
        return cost;
    }


    public StockType getStockType() {
        return stockType;
    }

    public boolean isSold() {
        return isSold;
    }

    public String getSoldAt() {
        return soldAt;
    }
}
