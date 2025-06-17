package com.pptk.certificate_examination_center.dao.impl;

import com.pptk.certificate_examination_center.dao.CustomerDao;
import com.pptk.certificate_examination_center.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Customer> selectAllCustomers() {
        String hql = "FROM Customer";
        return entityManager.createQuery(hql, Customer.class).getResultList();
    }
}
