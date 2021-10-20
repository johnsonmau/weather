package com.johnson.dev.weatherapp.controllers;

import com.johnson.dev.weatherapp.services.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {

    @Autowired
    private IndexService indexService;

    @GetMapping("/")
    public String getIndex(){
        return indexService.getIndex();
    }

    @GetMapping("/home")
    public String getIndex2(){
        return indexService.getIndex();
    }

    @GetMapping("/home/{unit}")
    public String getIndex(HttpServletRequest request, HttpServletResponse response, @PathVariable String unit,
                           @RequestParam(required = false) String location, Model model) {
        return indexService.getIndex(request, response, unit, location, model);
    }


}
