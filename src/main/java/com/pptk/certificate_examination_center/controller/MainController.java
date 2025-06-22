package com.pptk.certificate_examination_center.controller;

import com.pptk.certificate_examination_center.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.print.attribute.standard.SheetCollate;

@Controller
@RequestMapping("/")
public class MainController {
//    @GetMapping
//    public String index() {
//        return "index"; // This will resolve to src/main/resources/templates/index.html
//    }

    @Autowired
    private ScheduleService scheduleService;


    @GetMapping("/invoice")
    public  String invoice(){return "invoice";}


    @GetMapping("schedule")
    public  String schedule(){return "taolichthi";}
    @GetMapping("/login")
    public String index() {
        return "index"; // This will resolve to src/main/resources/templates/index.html
    }

    // tra cuu diem thi
    @GetMapping("/results")
    public String results() {
        return "ResultsExam";
    }

    @GetMapping("/results/detail")
    public String resultsDetail() {
        return "DetailResultsExam"; // This will resolve to src/main/resources/templates/ResultsExamDetail.html
    }

    @GetMapping("/list-registration-forms")
    public String registrationList() {
        return "listRegistrationPage";
    }

    @GetMapping("/detail-registration-form")
    public String detailRegistrationForm() {
        return "detailRegistrationFormPage";
    }
}
