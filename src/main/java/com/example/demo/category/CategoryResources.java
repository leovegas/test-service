package com.example.demo.category;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/categories")
public class CategoryResources {

    @GetMapping("")
   public String getAllCategories(HttpServletRequest request){

       return request.getAttribute("token") + "\n" +
               request.getRequestURI() + "\n";
    }
}
