package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Map;

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
    @GetMapping("status")
    public String status() {
        if (bank!=null) return "ok";
        return "not ok";
    }


    static class Ob {
        public String test1;
        public String test2;

        @Override
        public String toString() {
            return "Ob{" +
                    "test1='" + test1 + '\'' +
                    ", test2='" + test2 + '\'' +
                    '}';
        }
    }

    @PostMapping("/register")
    public String register(@RequestBody Ob ob){
        System.out.println(ob);
        return "ok";
    }

    public class ResponseTransfer {
        private String text;

        public ResponseTransfer(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        // standard getters/setters
    }

    @PostMapping(value = "/content", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseTransfer postResponseXmlContent(
            @RequestBody Ob ob) {
        return new ResponseTransfer(ob.test1);
    }


}
