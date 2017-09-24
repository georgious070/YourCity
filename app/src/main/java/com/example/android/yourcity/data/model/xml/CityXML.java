package com.example.android.yourcity.data.model.xml;


public class CityXML
{
    private Geonames geonames;

    public Geonames getGeonames ()
    {
        return geonames;
    }

    public void setGeonames (Geonames geonames)
    {
        this.geonames = geonames;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [geonames = "+geonames+"]";
    }
}