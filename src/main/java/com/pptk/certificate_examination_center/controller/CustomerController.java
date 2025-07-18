package com.pptk.certificate_examination_center.controller;

import com.pptk.certificate_examination_center.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers") // localhost:8080/customers
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<Object> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @GetMapping("/by-registration/{registrationId}")
    public ResponseEntity<Object> getCustomerByRegistrationId(@PathVariable Integer registrationId) {
        Object customer = customerService.getCustomerByRegistrationID(registrationId);
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy khách hàng.");
        }
        return ResponseEntity.ok(customer);
    }
}
