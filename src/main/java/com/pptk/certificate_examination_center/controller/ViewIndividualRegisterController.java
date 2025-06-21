package com.pptk.certificate_examination_center.controller;


import com.pptk.certificate_examination_center.service.ScheduleService;
import com.pptk.certificate_examination_center.service.impl.RegistrationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registrations/individual")
public class ViewIndividualRegisterController {
    @Autowired
    private ScheduleService  scheduleService;

    @GetMapping("/form")
//    @PreAuthorize("hasRole('ROLE_USER')")
    public String showForm(Model model){
        model.addAttribute("schedules", scheduleService.getAllSchedulesWithCertificate());
        return "individual-form";
    }

}
