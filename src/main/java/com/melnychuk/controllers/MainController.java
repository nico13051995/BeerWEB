package com.melnychuk.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.errors.ApiException;
import com.melnychuk.dao.interfaces.SalePointDao;
import com.melnychuk.entities.SalePoint;
import com.melnychuk.helpers.ExcelHelper;
import com.melnychuk.managers.SalePointManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
@Scope("session")
public class MainController
{
    private final SalePointManager salePointManager;
    private final SalePointDao salePointDao;

    private final ExcelHelper excelHelper;
    private final ObjectMapper mapper = new ObjectMapper();


    @Autowired
    public MainController(SalePointDao salePointDao, SalePointManager salePointManager, ExcelHelper excelHelper)
    {
        this.salePointDao = salePointDao;
        this.salePointManager = salePointManager;
        this.excelHelper = excelHelper;
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

    @RequestMapping(value = "/uploadFIle", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException, InterruptedException, ApiException
    {
        String name = null;

        if(!file.isEmpty())
        {
            name = file.getOriginalFilename();
        }

        File uploadedFile = new File(file.getOriginalFilename());
        file.transferTo(uploadedFile);

        String results = excelHelper.readFromExcel(uploadedFile);

        JsonNode jsonNode = mapper.readTree(mapper.writeValueAsString(results));

        return jsonNode.toString();
    }

    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public ModelAndView login()
    {
        ModelAndView model = new ModelAndView();
        model.setViewName("loginPage");

        return model;
    }
}
