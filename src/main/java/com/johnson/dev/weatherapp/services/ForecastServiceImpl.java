package com.johnson.dev.weatherapp.services;

import com.johnson.dev.weatherapp.models.Forecast;
import com.johnson.dev.weatherapp.models.GeocodedLocation;
import com.johnson.dev.weatherapp.models.LocationAndForecast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ForecastServiceImpl implements ForecastService {

    private static final Logger LOG = LoggerFactory.getLogger(ForecastServiceImpl.class);
    @Autowired
    private GeocoderService geocoderService;
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public Forecast getForecast(String loc) {

        GeocodedLocation geocodedLocation = geocoderService.geocode(loc);

        String longitude = geocodedLocation.getFeatures().get(0).getCoordinates().get(0).toString();
        String latitude = geocodedLocation.getFeatures().get(0).getCoordinates().get(1).toString();

        return getWeather(latitude, longitude);
    }

    @Override
    public LocationAndForecast getForecastAndLocation(String loc) {

        LOG.info("Entered Location: {}",loc);

        GeocodedLocation geocodedLocation = geocoderService.geocode(loc);

        if (geocodedLocation != null) {
            if (geocodedLocation.getFeatures().size() > 0) {

                String longitude = geocodedLocation.getFeatures().get(0).getCoordinates().get(0).toString();
                String latitude = geocodedLocation.getFeatures().get(0).getCoordinates().get(1).toString();

                Forecast forecast = getWeather(latitude, longitude);

                if (forecast != null) {
                    return new LocationAndForecast(geocodedLocation, forecast);
                }

            }
        }

        return null;
    }

    private Forecast getWeather(String lat, String lon){
        String apiKey = "f41fd73b971e31844dd49ea032087ae4";
        String temperatureUnit = "imperial";
        String reqUrl = "https://api.openweathermap.org/data/2.5/onecall?lat="+lat+"&lon="+lon+"&units="+temperatureUnit+"&appid="+apiKey;
        LOG.info("Weather URL {}",reqUrl);
        ResponseEntity<Forecast> weatherReq = restTemplate.getForEntity(reqUrl, Forecast.class);
        LOG.info("Successfully retrieved weather data");
        return weatherReq.getBody();
    }

}
