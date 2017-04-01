package com.teamshunya.silencio.Models;

/**
 * Created by himanshusingh on 02/04/17.
 */

public class PhoneNumber {
    private int _id;
    private String _phonenumber;

    public PhoneNumber() {
    }

    public PhoneNumber(String _phonenumber) {
        this._phonenumber = _phonenumber;
    }

    public void set_phonenumber(String _phonenumber) {
        this._phonenumber = _phonenumber;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_id() {
        return _id;
    }

    public String get_phonenumber() {
        return _phonenumber;
    }
}

