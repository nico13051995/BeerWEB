package com.melnychuk.managers;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.melnychuk.dao.interfaces.SalePointDao;
import com.melnychuk.entities.SalePoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

@Component
public class SalePointManagerImpl implements SalePointManager
{
    private final String API_KEY = "AIzaSyAUx8OFmTxpyDvCS-r4RQPAX6BLQDXKd8o";
    private final SalePointDao salePointDao;

    private static final double EARTH_RADIUS = 6371.; // Радиус Земли
    private DecimalFormat df = new DecimalFormat("#.##");

    private List<SalePoint> salePoints;
    private String userLocation;


    @Autowired
    public SalePointManagerImpl(SalePointDao salePointDao)
    {
        this.salePointDao = salePointDao;
    }


    @Override
    public List<SalePoint> getSalePoints(SalePoint myPos) throws IOException, ApiException, InterruptedException
    {
        salePoints = salePointDao.getSalePoints();
        userLocation = String.format("%s,%s", myPos.getLat(), myPos.getLng());

        Iterator<SalePoint> iterator = salePoints.iterator();

        while (iterator.hasNext())
        {
            SalePoint point = iterator.next();

            double dlng = Math.toRadians(myPos.getLng() - point.getLng());
            double dlat = Math.toRadians(myPos.getLat() - point.getLat());
            double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) + Math.cos(Math.toRadians(point.getLat())) * Math.cos(Math.toRadians(myPos.getLat())) * Math.sin(dlng / 2) * Math.sin(dlng / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

            double dist = c * EARTH_RADIUS;

            point.setDistance(dist);

//            if(point.getDistance() > 50D)
//                iterator.remove();
        }

        return salePoints;
    }

    @Override
    public LatLng makeGeocodeDataFromInfo(String address) throws InterruptedException, ApiException, IOException
    {
        GeoApiContext context = new GeoApiContext().setApiKey(API_KEY);

        final GeocodingApiRequest req = GeocodingApi.newRequest(context).address(address);
        // Synchronous
        GeocodingResult[] results = req.await();
        // Handle successful request.

        return results[0].geometry.location;
    }

    @Override
    public String getUserLocation()
    {
        return userLocation;
    }

    @Override
    public void setUserLocation(String address)
    {
        this.userLocation = address;
    }
}
