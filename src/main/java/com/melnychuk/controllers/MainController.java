package com.melnychuk.controllers;

import com.google.maps.errors.ApiException;
import com.melnychuk.dao.interfaces.SalePointDao;
import com.melnychuk.entities.SalePoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class MainController
{
    private final SalePointDao salePointDao;

    @Autowired
    public MainController(SalePointDao salePointDao)
    {
        this.salePointDao = salePointDao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView welcome(HttpServletRequest request) throws InterruptedException, ApiException, IOException
    {
        ModelAndView model = new ModelAndView();
        model.setViewName("index");

        return model;
    }

    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public ModelAndView pointInfo(@PathVariable int id) throws InterruptedException, ApiException, IOException
    {
        ModelAndView model = new ModelAndView();
        model.setViewName("info");

        SalePoint point = salePointDao.getPointById(id);
        model.getModel().put("point", point);

        return model;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView admin(HttpServletRequest request) throws InterruptedException, ApiException, IOException
    {
        ModelAndView model = new ModelAndView();
        model.setViewName("admin");
        return model;
    }
}
