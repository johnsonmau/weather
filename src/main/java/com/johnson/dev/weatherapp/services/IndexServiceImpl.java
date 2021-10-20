package com.johnson.dev.weatherapp.services;

import com.johnson.dev.weatherapp.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private ForecastService forecastService;

    @Override
    public String getIndex(HttpServletRequest request, HttpServletResponse response, String unit,
                           String location, Model model) {
        List<String> recentLocations = getRecentLocations(request);
        int locationSize = recentLocations.size();

        if (validUnit(unit) == false) unit = "f";

        if (unit.equalsIgnoreCase("f")) model.addAttribute("windUnit", "MPH");
        else{
            model.addAttribute("windUnit", "m/s");
        }

        model.addAttribute("unit", unit);

        if (locationSize > 0) {
            model.addAttribute("lastThreeLocs", recentLocations);
        }

        LocationAndForecast forecastAndLoc = null;
        if (location != null) {

            if (location.trim() != "") {
                model.addAttribute("inputLocation", location);

                forecastAndLoc = forecastService.getForecastAndLocation(location, unit);
            }

        }
        if (forecastAndLoc != null) {

            GeocodedLocation geocodedLocation = forecastAndLoc.getLocation();
            List<Feature> features = geocodedLocation.getFeatures();
            Feature feature = features.get(0);
            String placeName = feature.getPlaceName();
            Double longitude = feature.getCoordinates().get(0);
            Double latitude = feature.getCoordinates().get(1);

            model.addAttribute("longitude", longitude);
            model.addAttribute("latitude", latitude);

            String cookieName = "location" + locationSize;
            String cookieValue = placeName
                    .replaceAll(", ", "commaspace").replaceAll(" ", "space");

            addRecentLocation(request, response, cookieName, cookieValue);

            model.addAttribute("location", placeName);

            Forecast forecast = forecastAndLoc.getForecast();
            CurrentForecast currentForecast = forecast.getCurrent();
            List<DailyForecast> dailyForecasts = forecast.getDaily();
            List<HourForecast> hourlyForecasts = forecast.getHourly();
            model.addAttribute("dailyForecasts", dailyForecasts);
            model.addAttribute("currentForecast", currentForecast);
            model.addAttribute("hourlyForecast", hourlyForecasts);

        } else {
            if (location != null) model.addAttribute("error", "Cannot get weather data for the entered location.");
        }
        return "index";
    }

    @Override
    public String getIndex() {
        return "redirect:/home/f";
    }

    private boolean validUnit(String unit){
        return (unit.equalsIgnoreCase("c") == true || unit.equalsIgnoreCase("f") == true);
    }

    private void addRecentLocation(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                  String cookieValue){
        boolean cookieExists = false;

        if (request.getCookies() != null) {
            List<Cookie> cookieList = Arrays.asList(request.getCookies());

            for (Cookie cook : cookieList) {
                if (cook.getValue().equals(cookieValue)) {
                    cookieExists = true;
                    break;
                }
            }

            if (cookieExists == false) {
                Cookie locationCookie = new Cookie(cookieName, cookieValue);
                response.addCookie(locationCookie);
            }
        } else {
            Cookie locationCookie = new Cookie(cookieName, cookieValue);
            response.addCookie(locationCookie);
        }
    }

    private List<String> getRecentLocations(HttpServletRequest request) {
        Cookie[] existingCookies = request.getCookies();
        List<String> lastThreeLocNames = new ArrayList<>(3);

        if (existingCookies != null) {
            int j;

            if (existingCookies.length < 3) j = existingCookies.length;
            else j = 3;

            for (int i = existingCookies.length - 1; i >= 0; i--) {
                if (existingCookies[i].getName().contains("location")) {
                    String recentLocNameModified = existingCookies[i].getValue()
                            .replaceAll("commaspace", ", ").replace("space", " ");
                    lastThreeLocNames.add(recentLocNameModified);
                    if (lastThreeLocNames.size() == 3) break;
                }
            }
        }
        return lastThreeLocNames;
    }

}
