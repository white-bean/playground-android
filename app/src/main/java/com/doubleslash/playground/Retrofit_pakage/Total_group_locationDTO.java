package com.doubleslash.playground.Retrofit_pakage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Total_group_locationDTO {

    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("street")
    @Expose
    private String street;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

}
