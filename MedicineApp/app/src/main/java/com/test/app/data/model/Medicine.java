package com.test.app.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class Medicine implements Serializable {

    @SerializedName("id")
    @Expose
    @NonNull
    @PrimaryKey
    public int medicineId;
    @SerializedName("drugcode")
    @Expose
    public String drugcode;
    @SerializedName("brandname")
    @Expose
    public String brandname;
    @SerializedName("genericname")
    @Expose
    public String genericname;
    @SerializedName("packsize")
    @Expose
    public String packsize;
    @SerializedName("price")
    @Expose
    public String price;
    @SerializedName("company")
    @Expose
    public String company;
    @SerializedName("drugnature")
    @Expose
    public String drugnature;
    @SerializedName("drugtype")
    @Expose
    public String drugtype;
    @SerializedName("quantityavailable")
    @Expose
    public String quantityavailable;
    @SerializedName("available")
    @Expose
    public String available;
    @SerializedName("createdate")
    @Expose
    public String createdate;
    @SerializedName("updatedate")
    @Expose
    public String updatedate;
    @SerializedName("schedule")
    @Expose
    public String schedule;
    @SerializedName("discount")
    @Expose
    public String discount;
    @SerializedName("maxquantity")
    @Expose
    public String maxquantity;
    @SerializedName("genericdosage")
    @Expose
    public String genericdosage;
    @SerializedName("dosage")
    @Expose
    public String dosage;
    @SerializedName("sellingprice")
    @Expose
    public String sellingprice;
    @SerializedName("cimscategory")
    @Expose
    public String cimscategory;
    @SerializedName("cimssubcategory")
    @Expose
    public String cimssubcategory;
    @SerializedName("clasification")
    @Expose
    public String clasification;
    @SerializedName("category")
    @Expose
    public String category;
}
