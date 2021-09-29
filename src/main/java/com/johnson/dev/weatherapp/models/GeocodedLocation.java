package com.johnson.dev.weatherapp.models;

import java.util.List;

public class GeocodedLocation {
    private List<Feature> features;

    public GeocodedLocation() {
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return "GeocodedLocation{" +
                "features=" + features +
                '}';
    }
}
