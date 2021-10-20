package com.johnson.dev.weatherapp.services;

import com.johnson.dev.weatherapp.models.Forecast;
import com.johnson.dev.weatherapp.models.LocationAndForecast;


public interface ForecastService {
    Forecast getForecast(String loc, String unit);
    LocationAndForecast getForecastAndLocation(String loc, String unit);

}
