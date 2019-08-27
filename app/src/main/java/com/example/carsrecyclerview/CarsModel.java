package com.example.carsrecyclerview;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;



public class CarsModel {

    @SerializedName("ICERİK")
    @Expose
    private String iCERK;
    @SerializedName("FOTO")
    @Expose
    private String fOTO;
    @SerializedName("BASLIK")
    @Expose
    private String bASLIK;





    public String getICERK() {
        return iCERK;
    }

    public void setICERK(String iCERK) {
        this.iCERK = iCERK;
    }

    public String getFOTO() {
        return fOTO;
    }

    public void setFOTO(String fOTO) {
        this.fOTO = fOTO;
    }

    public String getBASLIK() {
        return bASLIK;
    }

    public void setBASLIK(String bASLIK) {
        this.bASLIK = bASLIK;
    }



}
