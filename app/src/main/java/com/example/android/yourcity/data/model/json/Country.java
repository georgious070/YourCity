
package com.example.android.yourcity.data.model.json;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country {

    @SerializedName("China")
    private List<String> china;
    @SerializedName("Japan")
    private List<String> japan;
    @SerializedName("Thailand")
    private List<String> thailand;

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
