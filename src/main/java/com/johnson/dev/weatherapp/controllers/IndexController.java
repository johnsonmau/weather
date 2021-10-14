package com.johnson.dev.weatherapp.controllers;

import com.johnson.dev.weatherapp.models.*;
import com.johnson.dev.weatherapp.services.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@Controller
public class IndexController {

    @Autowired
    private ForecastService forecastService;

    @GetMapping("/privacy")
    public String getPP(){
        return "privacy";
    }

    @GetMapping("/terms")
    public String getTerms(){
        return "terms";
    }

    @GetMapping("/")
    public String getIndex(){
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String getIndex(HttpServletRequest request, HttpServletResponse response,
                           @RequestParam(required = false) String location, Model model) {

        int locationSize = getRecentLocations(request).size();

        List<String> lastThreeLocNames = getRecentLocations(request);
        if (locationSize > 0) {
            model.addAttribute("lastThreeLocs", lastThreeLocNames);
        }

        LocationAndForecast forecastAndLoc = null;
        if (location != null) {

            if (location.trim() != "") {
                model.addAttribute("inputLocation", location);

                forecastAndLoc = forecastService.getForecastAndLocation(location);
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

    public void addRecentLocation(HttpServletRequest request, HttpServletResponse response, String cookieName,
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

    public List<String> getRecentLocations(HttpServletRequest request) {
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
