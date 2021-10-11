package com.johnson.dev.weatherapp.services;

import com.johnson.dev.weatherapp.models.GeocodedLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeocoderServiceImpl implements GeocoderService {

    private static final Logger LOG = LoggerFactory.getLogger(GeocoderServiceImpl.class);
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public GeocodedLocation geocode(String loc){
        String apiKey = "pk.eyJ1IjoibWF1cmljZWo5NSIsImEiOiJjanh1ZnVzMHAxNHV5M2Jyb2I5bXJ3c3BoIn0.sJjb1yKUOJKwtI4Hceqk-A";
        String reqUrl = "https://api.mapbox.com/geocoding/v5/mapbox.places/"+loc+".json?access_token="+apiKey;
        LOG.info("Geocode URL {}",reqUrl);
        ResponseEntity<GeocodedLocation> latLongReq = restTemplate.getForEntity(reqUrl, GeocodedLocation.class);
        LOG.info("geocoded location successfully");
        return latLongReq.getBody();
    }

}
