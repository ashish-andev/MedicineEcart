package com.test.app.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Cart {
    @PrimaryKey
    public int medicineId;
    public String drugcode;
    public String brandname;
    public String genericname;
    public String packsize;
    public String price;
    public String company;
    public String drugnature;
    public String drugtype;
    public String quantityavailable;
    public String available;
    public String createdate;
    public String updatedate;
    public String schedule;
    public String discount;
    public String maxquantity;
    public String genericdosage;
    public String dosage;
    public String sellingprice;
    public String cimscategory;
    public String cimssubcategory;
    public String clasification;
    public String category;
    public int quantity;

    public Cart() {

    }

    public Cart(Medicine medicine) {
        this.medicineId = medicine.medicineId;
        this.drugcode = medicine.drugcode;
        this.brandname = medicine.brandname;
        this.genericname = medicine.genericname;
        this.packsize = medicine.packsize;
        this.price = medicine.price;
        this.company = medicine.company;
        this.drugnature = medicine.drugnature;
        this.drugtype = medicine.drugtype;
        this.quantityavailable = medicine.quantityavailable;
        this.available = medicine.available;
        this.createdate = medicine.createdate;
        this.updatedate = medicine.updatedate;
        this.schedule = medicine.schedule;
        this.discount = medicine.discount;
        this.maxquantity = medicine.maxquantity;
        this.genericdosage = medicine.genericdosage;
        this.dosage = medicine.dosage;
        this.sellingprice = medicine.sellingprice;
        this.cimscategory = medicine.cimscategory;
        this.cimssubcategory = medicine.cimssubcategory;
        this.clasification = medicine.clasification;
        this.category = medicine.category;
    }
}
