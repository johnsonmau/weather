package com.johnson.dev.weatherapp.models;

public class MinuteForecast {

    private long dt;
    private int precipitation;

    public MinuteForecast() {
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public int getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(int precipitation) {
        this.precipitation = precipitation;
    }

    @Override
    public String toString() {
        return "MinuteForecast{" +
                "dt=" + dt +
                ", precipitation=" + precipitation +
                '}';
    }
}
