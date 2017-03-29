package com.melnychuk.managers;

import com.melnychuk.entities.SalePoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class SalePointManagerImpl implements SalePointManager
{
    private static final double EARTH_RADIUS = 6371.; // Радиус Земли
    private List<SalePoint> salePoints;

    @Override
    public List<SalePoint> getSalePoints(SalePoint myPos) throws IOException
    {
        salePoints = new ArrayList<SalePoint>();

        salePoints.add(new SalePoint("Drink beer here![1]", 49.8328609, 24.0179993));
        salePoints.add(new SalePoint("Drink beer here![2]", 49.8128717, 24.019363));
        salePoints.add(new SalePoint("Drink beer here![3]", 50.8128717, 25.019363));

        DecimalFormat df = new DecimalFormat("#.##");
        for (SalePoint point : salePoints)
        {
            double dlng = Math.toRadians(myPos.getLng() - point.getLng());
            double dlat = Math.toRadians(myPos.getLat() - point.getLat());
            double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) + Math.cos(Math.toRadians(point.getLat()))
                    * Math.cos(Math.toRadians(myPos.getLat())) * Math.sin(dlng / 2) * Math.sin(dlng / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

            point.setName(String.format("Distance: %skm", df.format(c * EARTH_RADIUS)));
        }

        return salePoints;
    }
}
