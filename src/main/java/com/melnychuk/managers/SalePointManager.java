package com.melnychuk.managers;

import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import com.melnychuk.entities.SalePoint;

import java.io.IOException;
import java.util.List;

public interface SalePointManager
{
    List<SalePoint> getSalePoints(SalePoint myPos) throws IOException, ApiException, InterruptedException;

    LatLng makeGeocodeDataFromInfo(String address) throws InterruptedException, ApiException, IOException;

    String getUserLocation();

    void setUserLocation(String address);
}
