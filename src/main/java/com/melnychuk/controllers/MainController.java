package com.melnychuk.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.errors.ApiException;
import com.melnychuk.dao.interfaces.BeerDao;
import com.melnychuk.dao.interfaces.SalePointDao;
import com.melnychuk.entities.Beer;
import com.melnychuk.entities.SalePoint;
import com.melnychuk.helpers.ExcelHelper;
import com.melnychuk.managers.SalePointManager;
import com.melnychuk.objects.UploadBeersAnswer;
import com.melnychuk.objects.UploadPointsAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@Scope("session")
public class MainController
{
    private final SalePointManager salePointManager;
    private final SalePointDao salePointDao;
    private final BeerDao beerDao;

    private final ExcelHelper excelHelper;
    private final ObjectMapper mapper = new ObjectMapper();


    @Autowired
    public MainController(SalePointDao salePointDao, SalePointManager salePointManager, ExcelHelper excelHelper, BeerDao beerDao)
    {
        this.salePointDao = salePointDao;
        this.salePointManager = salePointManager;
        this.excelHelper = excelHelper;
        this.beerDao = beerDao;
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

        List<Beer> beers = beerDao.getBeers();
        model.getModel().put("points", salePointDao.getSalePoints());
        model.getModel().put("beers", beers);

        return model;
    }

    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public ModelAndView login()
    {
        ModelAndView model = new ModelAndView();
        model.setViewName("loginPage");

        return model;
    }

    @RequestMapping(value = "/uploadPoints", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ModelAndView uploadPoints(@RequestParam("file") MultipartFile file) throws IOException, InterruptedException, ApiException
    {
        ModelAndView model = new ModelAndView();
        model.setViewName("admin");

        if(!file.isEmpty())
        {
            File uploadedFile = new File(file.getOriginalFilename());
            file.transferTo(uploadedFile);

            UploadPointsAnswer answer = excelHelper.readPointsFromExcel(uploadedFile);

            model.getModel().put("answer", answer);
        }

        return model;
    }

    @RequestMapping(value = "/uploadBeers", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ModelAndView uploadBeers(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) throws IOException, InterruptedException, ApiException
    {
        ModelAndView model = new ModelAndView();

        if(!file.isEmpty())
        {
            RedirectView redirectView = new RedirectView("admin");
            redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
            model.setView(redirectView);

            File uploadedFile = new File(file.getOriginalFilename());
            file.transferTo(uploadedFile);

            UploadBeersAnswer answer = excelHelper.readBeersFromExcel(uploadedFile);

            attributes.addFlashAttribute("beerAnswer", answer);
        }

        return model;
    }
}
