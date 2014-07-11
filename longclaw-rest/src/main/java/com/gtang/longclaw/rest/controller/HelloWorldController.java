package com.gtang.longclaw.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController { 

    @RequestMapping(value="/hello", method=RequestMethod.GET)
    public String hello(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        
        model.addAttribute("name", name);
        
        System.out.println("---------------------------------------------------------");
        System.out.println(name);
        System.out.println("model = " + model);
        System.out.println("---------------------------------------------------------");
        
        return "helloworld";
    }

}
