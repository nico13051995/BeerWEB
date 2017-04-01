package com.melnychuk.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.melnychuk.entities.SalePoint;
import com.melnychuk.managers.SalePointManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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
        File file = new File("E:\\WebProjects\\javaProjects\\BeerMap\\src\\main\\webapp\\WEB-INF\\resources\\GeoLiteCity.dat");
        LookupService cl = new LookupService(file.getAbsolutePath(), LookupService.GEOIP_MEMORY_CACHE | LookupService.GEOIP_CHECK_CACHE);
        String ipAddress = request.getRemoteAddr();
        Location location = cl.getLocation(ipAddress);
        SalePoint myPos = new SalePoint("My Location", location.latitude, location.longitude);

        List<SalePoint> salePoints = pointManager.getSalePoints(myPos);

        final ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(mapper.writeValueAsString(salePoints));

        return jsonNode.toString();
    }
}

