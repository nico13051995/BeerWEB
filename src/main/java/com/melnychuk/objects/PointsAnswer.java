package com.melnychuk.objects;

import com.melnychuk.entities.SalePoint;

import java.util.List;

public class PointsAnswer
{
    private String userIp;
    private SalePoint userLocation;
    private List<SalePoint> salePoints;

    public PointsAnswer()
    {
    }

    public PointsAnswer(String userIp, SalePoint userLocation, List<SalePoint> salePoints)
    {
        this.userIp = userIp;
        this.userLocation = userLocation;
        this.salePoints = salePoints;
    }

    public String getUserIp()
    {
        return userIp;
    }

    public void setUserIp(String userIp)
    {
        this.userIp = userIp;
    }

    public SalePoint getUserLocation()
    {
        return userLocation;
    }

    public void setUserLocation(SalePoint userLocation)
    {
        this.userLocation = userLocation;
    }

    public List<SalePoint> getSalePoints()
    {
        return salePoints;
    }

    public void setSalePoints(List<SalePoint> salePoints)
    {
        this.salePoints = salePoints;
    }
}
