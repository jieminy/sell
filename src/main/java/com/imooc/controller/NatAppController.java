package com.imooc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NatAppController {
    @GetMapping("/natapp")
    public String console(){
        return "natapp";
    }

}
