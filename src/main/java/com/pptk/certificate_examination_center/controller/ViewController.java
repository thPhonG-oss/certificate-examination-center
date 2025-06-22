package com.pptk.certificate_examination_center.controller;


import com.pptk.certificate_examination_center.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    @Autowired
    private ScheduleService  scheduleService;

    @GetMapping("/api/auth/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/dashboard")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String showDashboard(){
        return "index";
    }

    @GetMapping("registrations/individual/form")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String showForm(Model model){
        model.addAttribute("schedules", scheduleService.getAllSchedulesWithCertificate());
        return "individual-form";
    }
}
