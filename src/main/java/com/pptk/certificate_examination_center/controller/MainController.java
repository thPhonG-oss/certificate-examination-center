package com.pptk.certificate_examination_center.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    @GetMapping
    public String index() {
        return "index"; // This will resolve to src/main/resources/templates/index.html
    }

    @GetMapping("/invoice")
    public  String invoice(){return "invoice";}
    @GetMapping("schedule")
    public  String schedule(){return "taolichthi";}
}
