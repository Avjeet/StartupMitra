package com.teamshunya.silencio.Activities.ShowActivity.Fragments;

/**
 * Created by AVJEET on 21-03-2018.
 */

public class CategoriesModel {
    String title;
    int iconImageID;

    public CategoriesModel(String title, int iconImageID) {
        this.title = title;
        this.iconImageID = iconImageID;
    }




    public int getIconImageID() {
        return iconImageID;
    }

    public void setIconImageID(int iconImageID) {
        this.iconImageID = iconImageID;
    }


}
