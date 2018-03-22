package com.teamshunya.silencio.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by himanshusingh on 01/04/17.
 */

public class PersonalDetail {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("destination")
    @Expose
    private String destination;
    @SerializedName("dest_lat")
    @Expose
    private String destLat;
    @SerializedName("dest_long")
    @Expose
    private String destLong;
    @SerializedName("seat")
    @Expose
    private String seat;
    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("flightNo")
    @Expose
    private String flightNo;
    @SerializedName("pnr")
    @Expose
    private String pnr;
    @SerializedName("gate")
    @Expose
    private String gate;
    @SerializedName("eta")
    @Expose
    private String eta;
    @SerializedName("delay")
    @Expose
    private String delay;
    @SerializedName("comments")
    @Expose
    private String comments;

    /**
     * No args constructor for use in serialization
     */
    public PersonalDetail() {
    }

    /**
     * @param destLong
     * @param id
     * @param eta
     * @param logo
     * @param flightNo
     * @param source
     * @param seat
     * @param company
     * @param destLat
     * @param pnr
     * @param delay
     * @param comments
     * @param gate
     * @param destination
     */
    public PersonalDetail(String id, String source, String destination, String destLat, String destLong, String seat, String company, String logo, String flightNo, String pnr, String gate, String eta, String delay, String comments) {
        super();
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.destLat = destLat;
        this.destLong = destLong;
        this.seat = seat;
        this.company = company;
        this.logo = logo;
        this.flightNo = flightNo;
        this.pnr = pnr;
        this.gate = gate;
        this.eta = eta;
        this.delay = delay;
        this.comments = comments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestLat() {
        return destLat;
    }

    public void setDestLat(String destLat) {
        this.destLat = destLat;
    }

    public String getDestLong() {
        return destLong;
    }

    public void setDestLong(String destLong) {
        this.destLong = destLong;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}