package com.teamshunya.silencio.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by himanshusingh on 21/03/17.
 */

public class DepartureList {
    @SerializedName("Schemes")
    @Expose
    private List<Departure> departure = null;

    /**
     * No args constructor for use in serialization
     */
    public DepartureList() {
    }

    /**
     * @param departure
     */
    public DepartureList(List<Departure> departure) {
        super();
        this.departure = departure;
    }

    public List<Departure> getDeparture() {
        return departure;
    }

    public void setDeparture(List<Departure> departure) {
        this.departure = departure;
    }

}
