package com.test.app.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MedicineResponse {
    @SerializedName("medicines")
    @Expose
    public List<Medicine> medicines = null;
}
