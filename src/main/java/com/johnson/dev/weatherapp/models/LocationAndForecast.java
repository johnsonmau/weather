package com.johnson.dev.weatherapp.models;

public class LocationAndForecast {

    private GeocodedLocation location;
    private Forecast forecast;

    public LocationAndForecast(GeocodedLocation location, Forecast forecast) {
        this.location = location;
        this.forecast = forecast;
    }

    public GeocodedLocation getLocation() {
        return location;
    }

    public void setLocation(GeocodedLocation location) {
        this.location = location;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }
}
