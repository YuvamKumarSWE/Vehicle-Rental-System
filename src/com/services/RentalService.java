package com.services;
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
        System.out.println("Available Rentals:");
        boolean hasAvailable = false;
        for(Rentable r : availableRentals){
            if(r.isAvailable()){
                Vehicle v = (Vehicle) r;
                System.out.println(v.toString());
            }
        }
        if(!hasAvailable){
            System.out.println("No rentals currently available.");
        }
    }

    public void printAllRentals(){
        System.out.println("All Rentals:");
        if(availableRentals.isEmpty()){
            System.out.println("No rentals in system.");
        } else {
            for(Rentable r : availableRentals){
                System.out.println(r.toString());
            }
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
        System.out.println("Current Rentals:");
        if(rentals.isEmpty()){
            System.out.println("No active rentals.");
        } else {
            for(Map.Entry<User, Rentable> entry : rentals.entrySet()){
                System.out.println("User: " + entry.getKey().getName() + " -> Vehicle: " + entry.getValue().toString());
            }
        }
    }

}
