package com.johnson.dev.weatherapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MiscController {

    @GetMapping("/privacy")
    public String getPP(){
        return "privacy";
    }

    @GetMapping("/terms")
    public String getTerms(){
        return "terms";
    }

}
