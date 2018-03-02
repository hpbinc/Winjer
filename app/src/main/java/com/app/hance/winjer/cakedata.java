package com.app.hance.winjer;

/**
 * Created by hashim on 02/02/18.
 */

public class cakedata {

    public String ImageURL,id;
    public String ImageTitle;
    public String Description;
    public double price,discount;

    public void add(String id,String title,String ImageUrl,String description,double price,double discount)
    {

        this.id=id;
        this.ImageURL=ImageUrl;
        this.ImageTitle=title;
        this.Description=description;
        this.price=price;
        this.discount=discount;


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

    public double getprice() {

        return price;
    }

    public double getDiscount() {

        return discount;
    }

    public String getDescription() {

        return Description;
    }


}