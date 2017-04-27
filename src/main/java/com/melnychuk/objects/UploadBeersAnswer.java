package com.melnychuk.objects;

import com.melnychuk.entities.Beer;

import java.util.HashSet;
import java.util.Set;

public class UploadBeersAnswer
{
    private Set<Beer> newPoints = new HashSet<Beer>();
    private Set<Beer> updatedPoints = new HashSet<Beer>();

    public void addNew(Beer beer)
    {
        newPoints.add(beer);
    }

    public void addUpdated(Beer beer)
    {
        updatedPoints.add(beer);
    }

    public Set<Beer> getNewPoints()
    {
        return newPoints;
    }

    public Set<Beer> getUpdatedPoints()
    {
        return updatedPoints;
    }
}
