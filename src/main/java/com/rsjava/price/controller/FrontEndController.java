package com.rsjava.price.controller;

import com.rsjava.price.service.CityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FrontEndController {

   private CityService cityService;

    @GetMapping("/")
    public String geCityService(ModelMap map) {
        map.put("newService", new CityService());
        return "index";
    }

    @GetMapping("service")
    String getCalc(ModelMap map) {
        map.put("showPrice", cityService.getAverage());
        return "service";
    }

    @PostMapping("/add-service")
    public String addOperation(@ModelAttribute CityService cityService) {
        this.cityService = cityService;
        return "redirect:/service";
    }

}
