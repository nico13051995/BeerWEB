package com.melnychuk.managers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.melnychuk.entities.SalePoint;

import java.io.IOException;
import java.util.List;

public interface SalePointManager
{
    List<SalePoint> getSalePoints() throws IOException;

}
