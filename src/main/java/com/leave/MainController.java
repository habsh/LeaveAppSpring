package com.leave;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class MainController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    
    @RequestMapping("/add/{firstno}/{secondno}")
    public String addition(@PathVariable String firstno, @PathVariable String secondno) {
        int result = Integer.parseInt(firstno)+Integer.parseInt(secondno);
    	return String.valueOf(result);
    }
    

}