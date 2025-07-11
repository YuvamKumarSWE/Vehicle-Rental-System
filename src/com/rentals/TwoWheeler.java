package com.rentals;

public  class TwoWheeler extends Vehicle {
    public int cc;

    public TwoWheeler(int Id, String brand, String model, int baseRate, int cc){
        super(Id, brand, model, baseRate);
        this.cc = cc;
    }

    public int rentalCost(int days){
        return getBaseRate() * days * (cc/100);
    }
}
