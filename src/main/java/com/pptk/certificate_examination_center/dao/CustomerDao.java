package com.pptk.certificate_examination_center.dao;

import com.pptk.certificate_examination_center.entity.Customer;

import java.util.List;

public interface CustomerDao {
    List<Customer> selectAllCustomers();
}
