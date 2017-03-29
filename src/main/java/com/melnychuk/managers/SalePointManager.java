package com.melnychuk.managers;

import com.melnychuk.entities.SalePoint;

import java.io.IOException;
import java.util.List;

public interface SalePointManager
{
    List<SalePoint> getSalePoints(SalePoint myPos) throws IOException;
}
