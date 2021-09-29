package com.johnson.dev.weatherapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class HourForecast {

    private long dt;
    private int temp;
    @JsonProperty("feels_like")
    private int feelsLike;
    private long pressure;
    private int humidity;
    @JsonProperty("dew_point")
    private int dewPoint;
    private double uvi;
    private int clouds;
    private long visibility;
    @JsonProperty("wind_speed")
    private int windSpeed;
    @JsonProperty("wind_deg")
    private int windDeg;
    @JsonProperty("wind_gust")
    private int windGust;
    private List<Weather> weather;

    public HourForecast() {
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(int feelsLike) {
        this.feelsLike = feelsLike;
    }

    public long getPressure() {
        return pressure;
    }

    public void setPressure(long pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(int dewPoint) {
        this.dewPoint = dewPoint;
    }

    public double getUvi() {
        return uvi;
    }

    public void setUvi(double uvi) {
        this.uvi = uvi;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public long getVisibility() {
        return visibility;
    }

    public void setVisibility(long visibility) {
        this.visibility = visibility;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(int windDeg) {
        this.windDeg = windDeg;
    }

    public int getWindGust() {
        return windGust;
    }

    public void setWindGust(int windGust) {
        this.windGust = windGust;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "HourForecast{" +
                "dt=" + dt +
                ", temp=" + temp +
                ", feelsLike=" + feelsLike +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", dewPoint=" + dewPoint +
                ", uvi=" + uvi +
                ", clouds=" + clouds +
                ", visibility=" + visibility +
                ", windSpeed=" + windSpeed +
                ", windDeg=" + windDeg +
                ", windGust=" + windGust +
                ", weather=" + weather +
                '}';
    }

    public String unixDateConverter(Long unixTime, String pattern){
        Date date = new Date(unixTime*1000L);
        SimpleDateFormat jdf = new SimpleDateFormat(pattern);
        jdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
        String formattedDate = jdf.format(date);
        return formattedDate;
    }
}
