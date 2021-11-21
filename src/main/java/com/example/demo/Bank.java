package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class Bank {

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void deposit(double value) {
        this.money = money+value;
    }

    private double money;

}
