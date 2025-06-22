package com.pptk.certificate_examination_center.dao;

import java.util.List;

import com.pptk.certificate_examination_center.entity.Customer;

public interface CustomerDao {
    List<Customer> selectAllCustomers();
}


