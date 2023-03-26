package com.cbs.springcourse.rest.controllers;

import com.cbs.springcourse.rest.models.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // @Controller + @ResponseBody над кожним методом
@RequestMapping("/api")
public class FirstRESTController {

    @GetMapping("/sayHello")
    public String sayHello() {
         return "Hello world!";
    }

    @GetMapping("/test-page")
    public String testPage() {
        return "This is test page!";
    }

    @GetMapping("/person-page")
    public Person personPage() {
        return new Person();
    }
}
