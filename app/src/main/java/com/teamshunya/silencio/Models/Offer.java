package com.teamshunya.silencio.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by himanshusingh on 21/03/17.
 */

public class Offer {
    @SerializedName("expiry")
    @Expose
    private String expiry;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("offer")
    @Expose
    private String offer;
    @SerializedName("promocode")
    @Expose
    private String promocode;
    @SerializedName("tag")
    @Expose
    private String tag;

    /**
     * No args constructor for use in serialization
     */
    public Offer() {
    }

    /**
     * @param promocode
     * @param id
     * @param tag
     * @param name
     * @param img
     * @param offer
     * @param expiry
     */
    public Offer(String expiry, String id, String img, String name, String offer, String promocode, String tag) {
        super();
        this.expiry = expiry;
        this.id = id;
        this.img = img;
        this.name = name;
        this.offer = offer;
        this.promocode = promocode;
        this.tag = tag;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getPromocode() {
        return promocode;
    }

    public void setPromocode(String promocode) {
        this.promocode = promocode;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}
