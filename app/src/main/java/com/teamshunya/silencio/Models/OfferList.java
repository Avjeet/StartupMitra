package com.teamshunya.silencio.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by himanshusingh on 21/03/17.
 */

public class OfferList {
    @SerializedName("Offer")
    @Expose
    private List<Offer> offer = null;

    /**
     * No args constructor for use in serialization
     */
    public OfferList() {
    }

    /**
     * @param offer
     */
    public OfferList(List<Offer> offer) {
        super();
        this.offer = offer;
    }

    public List<Offer> getOffer() {
        return offer;
    }

    public void setOffer(List<Offer> offer) {
        this.offer = offer;
    }

}