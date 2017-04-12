package com.melnychuk.dao.interfaces;

import com.melnychuk.entities.SalePoint;

import java.util.List;

public interface SalePointDao
{
    List<SalePoint> getSalePoints();

    SalePoint getPointById(int id);
}
