package io.nkcm.jwt.jwtAuth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/home")
    public String welcome(){
        System.out.println("-----------------------------------------------------------");
        return "This is Homepage for JWT Authernication";
    }
    
}
