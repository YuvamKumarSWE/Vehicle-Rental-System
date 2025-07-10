package com.rentals;

public abstract class Vehicle {
    private int Id;
    private String brand;
    private String model;
    private int baseRate;

    public Vehicle(int Id, String brand, String model, int baseRate){
        this.Id = Id;
        this.brand = brand;
        this.model = model;
        this.baseRate = baseRate;
    }

    public abstract int rentalCost(int days);

    public void setId(int id) {
        Id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setBaseRate(int baseRate) {
        this.baseRate = baseRate;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getId() {
        return Id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel(){
        return model;
    }

    public int getBaseRate() {
        return baseRate;
    }
}
