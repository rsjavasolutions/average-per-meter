package com.rsjava.price.controller;

import com.rsjava.price.model.Flat;
import com.rsjava.price.service.CityService;
import com.rsjava.price.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FrontEndController {

    @Autowired
    private Flat flat;

    @GetMapping("/")
    public String geCityService(ModelMap map) {
        map.put("newService", flat);
        return "index";
    }

    @GetMapping("service")
    String getCalc(ModelMap map) {

        LinkService linkService = new LinkService(flat.getCity(), flat.getArea());
        CityService cityService = new CityService(linkService.getLink());

        map.put("showPrice", cityService.getAverage());
        return "service";
    }

    @PostMapping("/add-service")
    public String addOperation(@ModelAttribute Flat flat) {
        this.flat = flat;
        return "redirect:/service";
    }
}
