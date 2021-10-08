package com.johnson.dev.weatherapp.services;

import com.johnson.dev.weatherapp.models.GeocodedLocation;

public interface GeocoderService {

    GeocodedLocation geocode(String loc);
}
