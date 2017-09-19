
package com.example.android.yourcity.data.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country {

    @SerializedName("China")
    private List<String> china = null;
    @SerializedName("Japan")
    private List<String> japan = null;
    @SerializedName("Thailand")
    private List<String> thailand = null;

    public List<String> getChina() {
        return china;
    }

    public void setChina(List<String> china) {
        this.china = china;
    }

    public List<String> getJapan() {
        return japan;
    }

    public void setJapan(List<String> japan) {
        this.japan = japan;
    }

    public List<String> getThailand() {
        return thailand;
    }

    public void setThailand(List<String> thailand) {
        this.thailand = thailand;
    }

}
