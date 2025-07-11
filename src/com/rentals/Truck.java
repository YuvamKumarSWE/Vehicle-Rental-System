package com.rentals;

public  class Truck extends Vehicle {
    public int weightLoad;

    public Truck(int Id, String brand, String model, int baseRate, int weightLoad){
        super(Id, brand, model, baseRate);
        this.weightLoad = weightLoad;
    }

    public int rentalCost(int days){
        return getBaseRate() * days * (weightLoad/150);
    }
}
