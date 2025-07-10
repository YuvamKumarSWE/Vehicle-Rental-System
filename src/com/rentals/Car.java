package com.rentals;

public class Car extends Vehicle{

    private boolean isLuxury;

    public Car(int Id, String brand, String model, int baseRate, boolean isLuxury) {
        super(Id, brand, model, baseRate);
        this.isLuxury = isLuxury;
    }

    @Override
    public int rentalCost(int days){
        if(this.isLuxury){
            return days * (getBaseRate() * 2);
        } else{
            return days * getBaseRate();
        }
    }
}
