package com.app.hance.winjer;

/**
 * Created by hashim on 09/02/18.
 */


public class cleaningdata {


    private String service_type,home_type;

    private float cleaning_baseprice[],
            cleaning_discount[],
            balconyprice,
            bathroomprice,
            kitchenprice,
            hallprice;


    cleaningdata()
    {

        cleaning_baseprice = new float[6];
        cleaning_discount= new float[6];


    }


   public void setService_type(String type)
   {
       this.service_type=type;

   }

    public void setHome_type(String type)
    {
        this.home_type=type;

    }

     public void setbhk(int bhk,float price,float discount)
     {

         cleaning_baseprice[bhk]=price;
         cleaning_discount[bhk]=discount;


     }


     public float getpricebhk(int bhk)
     {

         return cleaning_baseprice[bhk];
     }

    public float getdiscountbhk(int bhk)
    {
          return cleaning_discount[bhk];

    }

    public void setothers(float a,float b,float c,float d)
    {
        bathroomprice=a;
        hallprice=b;
        kitchenprice=b;
        balconyprice=d;

    }

    public float getBathroomprice()
    {

        return  bathroomprice;
    }
    public float getHallprice()
    {

        return  hallprice;
    }
    public float getKitchenprice()
    {

        return  kitchenprice;
    }
    public float getBalconyprice()
    {

        return  balconyprice;
    }



}
