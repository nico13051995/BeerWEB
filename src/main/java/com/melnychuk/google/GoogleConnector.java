package com.melnychuk.google;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GoogleConnector
{
    public void connect() throws InterruptedException, ApiException, IOException
    {
        // Replace the API key below with a valid API key.
        GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyAUx8OFmTxpyDvCS-r4RQPAX6BLQDXKd8o");
        //         GeocodingResult[] results =  GeocodingApi.geocode(context,
        //                 "1600 Amphitheatre Parkway Mountain View, CA 94043").await();

//        GeocodingApi.reverseGeocode(context, new LatLng(49.8328609, 24.0179993)).await();
        GeocodingResult[] results = GeocodingApi.newRequest(context).components().await();
        System.out.println(results[0].formattedAddress);

        //         GeocodingApiRequest req = GeocodingApi.newRequest(context).address("Sydney");
        //
        //         try {
        //             req.await();
        //             // Handle successful request.
        //         } catch (Exception e) {
        //             // Handle error
        //         }
        //
        //         req.awaitIgnoreError(); // No checked exception.
    }
}
