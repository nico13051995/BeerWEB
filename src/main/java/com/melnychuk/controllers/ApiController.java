package com.melnychuk.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.melnychuk.entities.SalePoint;
import com.melnychuk.managers.SalePointManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "points/{coords:.+}", method = RequestMethod.GET)
    @ResponseBody
    public String welcome(@PathVariable String coords) throws IOException
    {
        String[] parsedCoords = coords.split(",");
        SalePoint myPos = new SalePoint(null, Double.parseDouble(parsedCoords[0]), Double.parseDouble(parsedCoords[1]));

        List<SalePoint> salePoints = pointManager.getSalePoints(myPos);

        final ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(mapper.writeValueAsString(salePoints));

        return jsonNode.toString();
    }
}

