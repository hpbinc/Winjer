package com.app.hance.winjer;

/**
 * Created by hashim on 30/01/18.
 */

public class data {

   private String
           plumbing_info,
           electrical_info,
           mobilerepair_info,
           tvrepair_info,
           acinfo,
           tailoring_info,
           lab_info,
           drycleaning_info,
           painting_info;        ;

   float   basic_home_cleaning_baseprice[],
           basic_home_cleaning_discount,
           basic_cleaning_balconyprice,
           basic_cleaning_bathroomprice,
           basic_cleaning_hallprice;

    float   deep_home_cleaning_baseprice[],
            deep_home_cleaning_discount,
            deep_cleaning_balconyprice,
            deep_cleaning_bathroomprice,
            deep_cleaning_hallprice;

    float   basic_apart_cleaning_baseprice[],
            basic_apart_cleaning_discount;

    float   deep_apart_cleaning_baseprice[],
            deep_apart_cleaning_discount;



    void addbasic_appartment()
    {


    }


    void addbasic_house()
    {


    }

    void adddeep_appartment()
    {


    }

   void adddeep_house()
    {



    }

    void setPlumbing_info(String plumbing_info)
    {

        this.plumbing_info=plumbing_info;

    }

    void setElectrical_info(String electrical_info)
    {
        this.electrical_info=electrical_info;

    }

   void setMobilerepair_info(String mobilerepair_info)
   {
       this.mobilerepair_info=mobilerepair_info;
   }
   void setAcinfo(String acinfo)
   {

       this.acinfo=acinfo;

   }

   void setLab(String lab_info)
   {
       this.lab_info=lab_info;


   }

   void setTailoring_info(String tailoring_info)
   {
       this.tailoring_info=tailoring_info;

   }

   void setTvrepair_info(String tvrepair_info)
   {
       this.tvrepair_info=tvrepair_info;
   }


   void setPainting_info(String painting_info)
   {
       this.painting_info=painting_info;

   }

   void setDrycleaning_info(String drycleaning_info)
   {
       this.drycleaning_info=drycleaning_info;
   }

   String getPlumbing_info()
   {
       return  plumbing_info;
   }

   String getElectrical_info()
   {

       return electrical_info;
   }

   String getMobilerepair_info()
   {
       return mobilerepair_info;
   }

   String getAcinfo()
   {return acinfo;}

   String getTvrepair_info()
   {return tvrepair_info;}

   String getTailoring_info()
   {
       return tailoring_info;
   }

   String getLab_info()
   {
       return lab_info;
   }

   String getPainting_info()
   {
       return painting_info;
   }
}
