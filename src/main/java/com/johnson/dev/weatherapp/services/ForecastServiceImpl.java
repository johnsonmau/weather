package com.johnson.dev.weatherapp.services;

import com.johnson.dev.weatherapp.models.Forecast;
import com.johnson.dev.weatherapp.models.GeocodedLocation;
import com.johnson.dev.weatherapp.models.LocationAndForecast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ForecastServiceImpl implements ForecastService {

    private static final Logger LOG = LoggerFactory.getLogger(ForecastServiceImpl.class);

    @Override
    public Forecast getForecast(String loc) {

        RestTemplate restTemplate = new RestTemplate();

        GeocodedLocation geocodedLocation = geocode(restTemplate, loc);

        LOG.info("{}",geocodedLocation);

        String longitude = geocodedLocation.getFeatures().get(0).getCoordinates().get(0).toString();
        String latitude = geocodedLocation.getFeatures().get(0).getCoordinates().get(1).toString();

        return getWeather(restTemplate, latitude, longitude);
    }

    @Override
    public LocationAndForecast getForecastAndLocation(String loc) {

        LOG.info("Entered Location: {}",loc);

        RestTemplate restTemplate = new RestTemplate();

        GeocodedLocation geocodedLocation = geocode(restTemplate, loc);

        LOG.info("{}",geocodedLocation);

        if (geocodedLocation != null) {
            if (geocodedLocation.getFeatures().size() > 0) {

                String longitude = geocodedLocation.getFeatures().get(0).getCoordinates().get(0).toString();
                String latitude = geocodedLocation.getFeatures().get(0).getCoordinates().get(1).toString();

                Forecast forecast = getWeather(restTemplate, latitude, longitude);

                if (forecast != null) {
                    return new LocationAndForecast(geocodedLocation, forecast);
                }

            }
        }

        return null;
    }

    public GeocodedLocation geocode(RestTemplate restTemplate, String loc){
        String apiKey = "pk.eyJ1IjoibWF1cmljZWo5NSIsImEiOiJjanh1ZnVzMHAxNHV5M2Jyb2I5bXJ3c3BoIn0.sJjb1yKUOJKwtI4Hceqk-A";
        String reqUrl = "https://api.mapbox.com/geocoding/v5/mapbox.places/"+loc+".json?access_token="+apiKey;
        System.out.println("Geocode URL: "+reqUrl);
        ResponseEntity<GeocodedLocation> latLongReq = restTemplate.getForEntity(reqUrl, GeocodedLocation.class);
        return latLongReq.getBody();
    }

    public Forecast getWeather(RestTemplate restTemplate, String lat, String lon){
        String apiKey = "f41fd73b971e31844dd49ea032087ae4";
        String temperatureUnit = "imperial";
        String reqUrl = "https://api.openweathermap.org/data/2.5/onecall?lat="+lat+"&lon="+lon+"&units="+temperatureUnit+"&appid="+apiKey;
        System.out.println("Weather URL: "+reqUrl);
        ResponseEntity<Forecast> latLongReq = restTemplate.getForEntity(reqUrl, Forecast.class);
        LOG.info("WeatherData{}",latLongReq.getBody());
        return latLongReq.getBody();
    }

}
