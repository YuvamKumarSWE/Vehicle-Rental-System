package com.services;
import com.*;
import com.rentals.Rentable;
import com.rentals.Vehicle;
import com.user.User;

import java.util.*;

public class RentalService {
    private List<Rentable> availableRentals = new ArrayList<>();
    private Map<User,Rentable> rentals = new HashMap<>();

    public void addRentals(Rentable rentable){
        this.availableRentals.add(rentable);
    }

    public void printAvailableRentals(){
        for(Rentable r : availableRentals){
            if(r.isAvailable()){
                System.out.println(r);
            }
        }
    }

    public void printAllRentals(){
        for(Rentable r : availableRentals){
            System.out.println(r);
        }
    }

    public void rent(User u, int Id, int days){
        if(u.getAge() < 18 ){
            System.out.println("Sorry can not be rented. You are underage");
            return;
        }

        for(Rentable r: availableRentals){
            if(r instanceof Vehicle v){
                if(v.getId() == Id && v.isAvailable()){
                    v.setAvailable(false);
                    int cost = v.rentalCost(days);
                    u.addBalance(cost);
                    rentals.put(u,r);
                    System.out.println("Vehicle " + v.getId() + " rented successfully! Cost: " + cost + " for " + days + " days");
                    return;
                }
            }
        }
        System.out.println("Vehicle with ID " + Id + " not found or not available");
    }

    public void printMap(){
        System.out.println(rentals);
    }

}
