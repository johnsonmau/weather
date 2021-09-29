package com.johnson.dev.weatherapp.controllers;

import com.johnson.dev.weatherapp.models.Forecast;
import com.johnson.dev.weatherapp.services.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForecastRestController {

    @Autowired
    private ForecastService forecastService;

    @GetMapping("/rest/forecast/v1/status")
    public String status(){
        return "Weather application is working.";
    }

    @GetMapping(value = "/rest/forecast/v1", produces = "application/json")
    public @ResponseBody Forecast getForecast(@RequestParam String loc){
        return forecastService.getForecast(loc);
    }
}
