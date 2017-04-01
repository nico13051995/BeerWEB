package com.melnychuk.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.melnychuk.entities.SalePoint;
import com.melnychuk.managers.SalePointManager;
import com.melnychuk.objects.PointsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

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

    @RequestMapping(value = "/points", method = RequestMethod.GET)
    @ResponseBody
    public String welcome(HttpServletRequest request) throws IOException
    {
        String path = request.getSession().getServletContext().getRealPath("/WEB-INF/classes/GeoLiteCity.dat");
        System.out.println(request.getSession().getServletContext().getRealPath("/WEB-INF/classes/GeoLiteCity.dat"));
        LookupService cl = new LookupService(path.toString(), LookupService.GEOIP_MEMORY_CACHE | LookupService.GEOIP_CHECK_CACHE);
        String ipAddress = request.getRemoteAddr();
        Location location = cl.getLocation(ipAddress);
        if (location == null) location = cl.getLocation("95.69.203.155");
        SalePoint myPos = new SalePoint("My Location", location.latitude, location.longitude);

        List<SalePoint> salePoints = pointManager.getSalePoints(myPos);

        PointsHelper helper = new PointsHelper();
        helper.setUserIp(request.getRemoteAddr());
        helper.setUserLocation(myPos);
        helper.setSalePoints(salePoints);

        final ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(mapper.writeValueAsString(helper));

        return jsonNode.toString();
    }
}

