package com.melnychuk.dao.interfaces;

import com.melnychuk.entities.Beer;

import java.util.List;

public interface BeerDao
{
    List<Beer> getBeers();

    Beer getBeerByName(String name);

    void save(Beer beer);
}
