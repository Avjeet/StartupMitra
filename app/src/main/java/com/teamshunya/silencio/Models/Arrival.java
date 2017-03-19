package com.teamshunya.silencio.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by himanshusingh on 19/03/17.
 */
public class Arrival {

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
    @SerializedName("source")
    @Expose
    private String source;

    /**
     * No args constructor for use in serialization
     *
     */
    public Arrival() {
    }

    /**
     *
     * @param id
     * @param logo
     * @param eta
     * @param flightNo
     * @param source
     * @param company
     * @param delay
     * @param gate
     * @param destination
     */
    public Arrival(String company, String delay, String destination, String eta, String flightNo, String gate, String id, String logo, String source) {
        super();
        this.company = company;
        this.delay = delay;
        this.destination = destination;
        this.eta = eta;
        this.flightNo = flightNo;
        this.gate = gate;
        this.id = id;
        this.logo = logo;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

}