package com.johnson.dev.weatherapp.services;

import org.springframework.ui.Model;

public interface IndexService {
    String getIndex(String location, String country, Model model);
}
