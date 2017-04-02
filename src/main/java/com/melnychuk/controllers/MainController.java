package com.melnychuk.controllers;

import com.google.maps.errors.ApiException;
import com.melnychuk.google.GoogleConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class MainController
{
    private final GoogleConnector googleConnector;

    @Autowired
    public MainController(GoogleConnector googleConnector)
    {
        this.googleConnector = googleConnector;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView welcome(HttpServletRequest request) throws InterruptedException, ApiException, IOException
    {
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        return model;
    }

    @RequestMapping(value = "/y", method = RequestMethod.GET)
    public ModelAndView yandex(HttpServletRequest request) throws InterruptedException, ApiException, IOException
    {
        ModelAndView model = new ModelAndView();
        model.setViewName("yandex");
        return model;
    }
}
