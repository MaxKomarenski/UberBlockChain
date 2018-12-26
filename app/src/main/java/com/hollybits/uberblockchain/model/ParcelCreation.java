package com.hollybits.uberblockchain.model;

public class ParcelCreation {
    private Long fromId;
    private String email;
    private Coordinates addressTo;
    private Coordinates from;
    private double price;
    private String description;

    public ParcelCreation(Long fromId, String email, Coordinates addressTo, Coordinates from, double price, String description) {
        this.fromId = fromId;
        this.email = email;
        this.addressTo = addressTo;
        this.from = from;
        this.price = price;
        this.description = description;
    }

    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public Coordinates getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(Coordinates addressTo) {
        this.addressTo = addressTo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Coordinates getFrom() {
        return from;
    }

    public void setFrom(Coordinates from) {
        this.from = from;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
