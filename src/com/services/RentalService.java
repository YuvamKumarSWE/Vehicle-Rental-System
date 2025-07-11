package com.services;

import com.rentals.Rentable;
import com.rentals.Vehicle;
import com.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RentalService {
    private final List<Rentable> availableRentals = new ArrayList<>();
    private final Map<User, Rentable> rentals = new HashMap<>();
    private final List<User> users = new ArrayList<>();

    public void addUser(User user) {
        this.users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public void addRentals(Rentable rentable) {
        this.availableRentals.add(rentable);
    }

    public List<Rentable> getAvailableRentals() {
        List<Rentable> available = new ArrayList<>();
        for (Rentable r : availableRentals) {
            if (r.isAvailable()) {
                available.add(r);
            }
        }
        return available;
    }

    public List<Rentable> getAllRentals() {
        return availableRentals;
    }


    public String rent(User u, int Id, int days) {
        if (u.getAge() < 18) {
            return "Sorry can not be rented. You are underage";
        }

        for (Rentable r : availableRentals) {
            if (r instanceof Vehicle v) {
                if (v.getId() == Id && v.isAvailable()) {
                    v.setAvailable(false);
                    int cost = v.rentalCost(days);
                    u.addBalance(cost);
                    rentals.put(u, r);
                    return "Vehicle " + v.getId() + " rented successfully! Cost: " + cost + " for " + days + " days";
                }
            }
        }
        return "Vehicle with ID " + Id + " not found or not available";
    }

    public Map<User, Rentable> getRented() {
        return rentals;
    }

}
