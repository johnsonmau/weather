package com.johnson.dev.weatherapp.services;

import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IndexService {
    String getIndex(HttpServletRequest request, HttpServletResponse response, String unit, String location, Model model);
    String getIndex();
}
