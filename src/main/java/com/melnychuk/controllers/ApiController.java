package com.melnychuk.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.errors.ApiException;
import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.melnychuk.entities.SalePoint;
import com.melnychuk.managers.SalePointManager;
import com.melnychuk.objects.PointsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ApiController
{
    private final SalePointManager pointManager;

    @Autowired
    public ApiController(SalePointManager pointManager)
    {
        this.pointManager = pointManager;
    }

    @RequestMapping(value = "/points/{coords:.+}", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String points(HttpServletRequest request, @PathVariable String coords) throws IOException, InterruptedException, ApiException
    {
        PointsHelper helper = new PointsHelper();

        SalePoint myPos;
        if(coords.equals("null")) myPos = getMyPositionByIp(request);
        else myPos = new SalePoint("My Location", Double.parseDouble(coords.split(",")[0]), Double.parseDouble(coords.split(",")[1]));

        helper.setUserIp(request.getRemoteAddr());
        helper.setUserLocation(myPos);
        helper.setSalePoints(pointManager.getSalePoints(myPos));

        final ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(mapper.writeValueAsString(helper));

        return jsonNode.toString();
    }

    private SalePoint getMyPositionByIp(HttpServletRequest request) throws IOException
    {
        String path = request.getSession().getServletContext().getRealPath("/WEB-INF/classes/GeoLiteCity.dat");
        LookupService cl = new LookupService(path, LookupService.GEOIP_MEMORY_CACHE | LookupService.GEOIP_CHECK_CACHE);

        String ipAddress = request.getRemoteAddr();
        Location location = cl.getLocation(ipAddress);

        //for locahost only
        if (location == null) location = cl.getLocation("95.69.203.155");

        return new SalePoint("My Location", location.latitude, location.longitude);
    }
}

