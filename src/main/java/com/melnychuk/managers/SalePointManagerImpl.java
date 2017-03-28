package com.melnychuk.managers;

import com.melnychuk.entities.SalePoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SalePointManagerImpl implements SalePointManager
{
    private List<SalePoint> salePoints;

    @Override
    public List<SalePoint> getSalePoints() throws IOException
    {
        salePoints = new ArrayList<SalePoint>();

        salePoints.add(new SalePoint("Drink beer here![1]", 49.8328609, 24.0179993));
        salePoints.add(new SalePoint("Drink beer here![2]", 49.8128717, 24.019363));
        salePoints.add(new SalePoint("Drink beer here![3]", 50.8128717, 25.019363));

        return salePoints;
    }
}
