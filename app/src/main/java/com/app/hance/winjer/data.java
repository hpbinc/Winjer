package com.app.hance.winjer;


public class data {

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
    private String
            plumbing_info,
            electrical_info,
            mobilerepair_info,
            tvrepair_info,
            acinfo,
            tailoring_info,
            lab_info,
            drycleaning_info,
            painting_info,
            carpet_info,
            carpet_price,
            sofa_info,
            sofa_price;

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

    void setLab(String lab_info)
    {
        this.lab_info=lab_info;


    }

    void setDrycleaning_info(String drycleaning_info)
    {
        this.drycleaning_info=drycleaning_info;
    }

    String getPlumbing_info()
    {
        return  plumbing_info;
    }

    void setPlumbing_info(String plumbing_info)
    {

        this.plumbing_info=plumbing_info;

    }

    String getElectrical_info()
    {

        return electrical_info;
    }

    void setElectrical_info(String electrical_info)
    {
        this.electrical_info=electrical_info;

    }

    String getMobilerepair_info()
    {
        return mobilerepair_info;
    }

    void setMobilerepair_info(String mobilerepair_info)
    {
        this.mobilerepair_info=mobilerepair_info;
    }

    String getAcinfo()
    {return acinfo;}

    void setAcinfo(String acinfo)
    {

        this.acinfo=acinfo;

    }

    String getTvrepair_info()
    {return tvrepair_info;}

    void setTvrepair_info(String tvrepair_info)
    {
        this.tvrepair_info=tvrepair_info;
    }

    String getTailoring_info()
    {
        return tailoring_info;
    }

    void setTailoring_info(String tailoring_info)
    {
        this.tailoring_info=tailoring_info;

    }

    String getLab_info()
    {
        return lab_info;
    }

    String getPainting_info()
    {
        return painting_info;
    }

    void setPainting_info(String painting_info)
    {
        this.painting_info=painting_info;

    }

    void setcarpetdata(String carpet_info,String carpet_price)
    { this.carpet_info=carpet_info;
        this.carpet_price=carpet_price;
    }

    void setsofadata(String sofa_info,String sofa_price)
    {
        this.sofa_info=sofa_info;
        this.sofa_price=sofa_price;
    }

    String getCarpet_info()
    {return  carpet_info;}

    String getCarpet_price()
    {return carpet_price;}

    String getSofa_info()
    {return  sofa_info;}

    String getSofa_price()
    {
        return sofa_price;
    }



}
