package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Controller {

    @Autowired
    private Bank bank;

    @GetMapping("deposit/{value}")
    public double deposit(@PathVariable double value) {
        bank.deposit(value);
        return bank.getMoney();
    }
    @GetMapping("balance")
    public double balance() {
        return bank.getMoney();
    }
}
