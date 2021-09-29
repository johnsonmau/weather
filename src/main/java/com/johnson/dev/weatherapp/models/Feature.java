package com.johnson.dev.weatherapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Feature {

    @JsonProperty("place_name")
    private String placeName;
    @JsonProperty("place_type")
    private List<String> placeType;
    @JsonProperty("center")
    private List<Double> coordinates;

    public Feature() {
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public List<String> getPlaceType() {
        return placeType;
    }

    public void setPlaceType(List<String> placeType) {
        this.placeType = placeType;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "Feature{" +
                "placeName='" + placeName + '\'' +
                ", placeType=" + placeType +
                ", coordinates=" + coordinates +
                '}';
    }
}
