package com.melnychuk.objects;

import com.melnychuk.entities.SalePoint;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class UploadPointsAnswer
{
    private Set<SalePoint> newPoints = new HashSet<SalePoint>();
    private Set<SalePoint> updatedPoints = new HashSet<SalePoint>();

    private List<String> messages = new ArrayList<String>();

    public void addNew(SalePoint point)
    {
        newPoints.add(point);
    }

    public void addUpdated(SalePoint point)
    {
        updatedPoints.add(point);
    }

    public Set<SalePoint> getNewPoints()
    {
        return newPoints;
    }

    public Set<SalePoint> getUpdatedPoints()
    {
        return updatedPoints;
    }

    public void AddMsg(String msg) { messages.add(msg); }

    public List<String> getMessages()
    {
        return messages;
    }
}
