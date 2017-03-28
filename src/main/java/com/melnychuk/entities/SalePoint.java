package com.melnychuk.entities;

public class SalePoint
{
    private int id;

    private String name;
    private double lat;
    private double lng;

    public SalePoint(String name, double lat, double lng)
    {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getLat()
    {
        return lat;
    }

    public void setLat(double lat)
    {
        this.lat = lat;
    }

    public double getLng()
    {
        return lng;
    }

    public void setLng(double lng)
    {
        this.lng = lng;
    }
}
