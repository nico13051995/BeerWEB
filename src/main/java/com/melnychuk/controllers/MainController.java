package com.melnychuk.controllers;

import com.google.maps.errors.ApiException;
import com.melnychuk.dao.interfaces.SalePointDao;
import com.melnychuk.entities.SalePoint;
import com.melnychuk.managers.SalePointManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@Scope("session")
public class MainController
{
    private final SalePointManager salePointManager;
    private final SalePointDao salePointDao;

    @Autowired
    public MainController(SalePointDao salePointDao, SalePointManager salePointManager)
    {
        this.salePointDao = salePointDao;
        this.salePointManager = salePointManager;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView welcome(HttpServletRequest request)
    {
        ModelAndView model = new ModelAndView();
        model.setViewName("index");

        return model;
    }

    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public ModelAndView pointInfo(HttpServletRequest request, @PathVariable int id)
    {
        ModelAndView model = new ModelAndView();
        model.setViewName("info");

        SalePoint point = salePointDao.getPointById(id);
        String userLocation = salePointManager.getUserLocation();

        model.getModel().put("point", point);
        model.getModel().put("userLocation", userLocation);

        return model;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView admin(HttpServletRequest request) throws InterruptedException, ApiException, IOException
    {
        ModelAndView model = new ModelAndView();
        model.setViewName("admin");
        return model;
    }

    @RequestMapping(value = "/uploadFIle", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file)
    {
        String name = null;

        if(!file.isEmpty())
        {
            name = file.getOriginalFilename();
        }

        return name;
    }
}
