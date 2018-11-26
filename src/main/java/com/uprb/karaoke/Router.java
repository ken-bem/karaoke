package com.uprb.karaoke;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Router {

    @RequestMapping("/")
    public String home(){
        return "layout" ;
    }
}
