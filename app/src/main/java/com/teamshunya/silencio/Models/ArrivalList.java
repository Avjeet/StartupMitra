package com.teamshunya.silencio.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by himanshusingh on 19/03/17.
 */
public class ArrivalList {@SerializedName("Arrivals")
@Expose
private List<Arrival> arrivals = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public ArrivalList() {
    }

    /**
     *
     * @param arrivals
     */
    public ArrivalList(List<Arrival> arrivals) {
        super();
        this.arrivals = arrivals;
    }

    public List<Arrival> getArrivals() {
        return arrivals;
    }

    public void setArrivals(List<Arrival> arrivals) {
        this.arrivals = arrivals;
    }

}