package com.example.myapplication.data.responses;

import com.google.gson.annotations.SerializedName;

public class BrandResponse {
    @SerializedName("_id")
    public String Id;
    @SerializedName("name")
    public String Name;

    public BrandResponse(String id, String name) {
        Id = id;
        Name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
