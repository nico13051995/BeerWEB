package com.melnychuk.objects;

import com.melnychuk.entities.SalePoint;

public class ExcelParseResultForPoints
{
    private SalePoint point;
    private String beerName;
    private boolean[] joins;

    public ExcelParseResultForPoints(SalePoint point, String beerName, boolean[] joins)
    {
        this.point = point;
        this.beerName = beerName;
        this.joins = joins;
    }

    public SalePoint getPoint()
    {
        return point;
    }

    public void setPoint(SalePoint point)
    {
        this.point = point;
    }

    public String getBeerName()
    {
        return beerName;
    }

    public void setBeerName(String beerName)
    {
        this.beerName = beerName;
    }

    public boolean[] getJoins()
    {
        return joins;
    }

    public void setJoins(boolean[] joins)
    {
        this.joins = joins;
    }
}
