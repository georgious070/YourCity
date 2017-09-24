package com.example.android.yourcity.data.model.xml;

import com.example.android.yourcity.data.model.xml.Entry;

public class Geonames {
    private Entry entry;

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    @Override
    public String toString() {
        return "ClassPojo [entry = " + entry + "]";
    }
}
