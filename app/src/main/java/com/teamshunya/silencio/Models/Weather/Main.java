package com.teamshunya.silencio.Models.Weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by himanshusingh on 22/03/17.
 */

public class Main {

    @SerializedName("temp")
    @Expose
    private Double temp;
    @SerializedName("humidity")
    @Expose
    private Integer humidity;
    @SerializedName("pressure")
    @Expose
    private Integer pressure;
    @SerializedName("temp_min")
    @Expose
    private Double tempMin;
    @SerializedName("temp_max")
    @Expose
    private Double tempMax;

    /**
     * No args constructor for use in serialization
     *
     */
    public Main() {
    }

    /**
     *
     * @param humidity
     * @param pressure
     * @param tempMax
     * @param temp
     * @param tempMin
     */
    public Main(Double temp, Integer humidity, Integer pressure, Double tempMin, Double tempMax) {
        super();
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Double getTempMin() {
        return tempMin;
    }

    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    public Double getTempMax() {
        return tempMax;
    }

    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

}
