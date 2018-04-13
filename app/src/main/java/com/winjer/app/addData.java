package com.winjer.app;

/*
 *
 * Created by hashim on 02/02/18.
 */

public class addData {

    public String ImageURL,id;
    public String ImageTitle;
    public String Description;
    public double price,discount;

    public void add(String id, String title, String ImageUrl)
    {

        this.id=id;
        this.ImageURL=ImageUrl;
        this.ImageTitle=title;
    }

    public String getId(){

        return id;
    }

    public String getImageUrl() {

        return ImageURL;
    }

    public void setImageUrl(String imageServerUrl) {

        this.ImageURL = imageServerUrl;
    }

    public String getImageTitle() {

        return ImageTitle;
    }

    public void setImageTitle(String Imagetitlename) {

        this.ImageTitle = Imagetitlename;
    }

}