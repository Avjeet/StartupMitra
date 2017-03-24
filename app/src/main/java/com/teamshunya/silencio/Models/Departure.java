package com.teamshunya.silencio.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by himanshusingh on 21/03/17.
 */

public class Departure {

    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("delay")
    @Expose
    private String delay;
    @SerializedName("destination")
    @Expose
    private String destination;
    @SerializedName("eta")
    @Expose
    private String eta;
    @SerializedName("flightNo")
    @Expose
    private String flightNo;
    @SerializedName("gate")
    @Expose
    private String gate;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("pnr")
    @Expose
    private String pnr;
    @SerializedName("seat")
    @Expose
    private String seat;
    @SerializedName("source")
    @Expose
    private String source;
    private Departure departure;

    /**
     * No args constructor for use in serialization
     *
     */
    public Departure() {
    }

    /**
     *
     * @param id
     * @param logo
     * @param eta
     * @param flightNo
     * @param source
     * @param seat
     * @param company
     * @param pnr
     * @param delay
     * @param gate
     * @param destination
     */
    public Departure(String company, String delay, String destination, String eta, String flightNo, String gate, String id, String logo, String pnr, String seat, String source) {
        super();
        this.company = company;
        this.delay = delay;
        this.destination = destination;
        this.eta = eta;
        this.flightNo = flightNo;
        this.gate = gate;
        this.id = id;
        this.logo = logo;
        this.pnr = pnr;
        this.seat = seat;
        this.source = source;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Departure getDeparture() {
        return departure;
    }
}
