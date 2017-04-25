package com.melnychuk.dao.interfaces;

import com.melnychuk.entities.Beer;

public interface BeerDao
{
    Beer getBeerByName(String name);
}
