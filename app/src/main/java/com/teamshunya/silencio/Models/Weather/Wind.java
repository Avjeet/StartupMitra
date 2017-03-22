package com.teamshunya.silencio.Models.Weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by himanshusingh on 22/03/17.
 */
public class Wind {

    @SerializedName("speed")
    @Expose
    private Double speed;
    @SerializedName("deg")
    @Expose
    private Double deg;

    /**
     * No args constructor for use in serialization
     *
     */
    public Wind() {
    }

    /**
     *
     * @param speed
     * @param deg
     */
    public Wind(Double speed, Double deg) {
        super();
        this.speed = speed;
        this.deg = deg;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getDeg() {
        return deg;
    }

    public void setDeg(Double deg) {
        this.deg = deg;
    }

}
