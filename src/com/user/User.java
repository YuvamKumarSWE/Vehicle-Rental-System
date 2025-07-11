package com.user;

public class User {
    private int Id;
    private String name;
    private int age;
    private int accountBalance = 0;

    public User(int Id, String name, int age){
        this.Id = Id;
        this.name = name;
        this.age = age;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance){
        this.accountBalance = accountBalance;
    }

    public void addBalance(int cost){
        this.accountBalance += cost;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", name='" + name +  ", age=" + age +
                 "accountBalance=" + accountBalance +
                '}';
    }
}
