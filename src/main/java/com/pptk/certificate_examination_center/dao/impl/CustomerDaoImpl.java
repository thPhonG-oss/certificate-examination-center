package com.pptk.certificate_examination_center.dao.impl;

import com.pptk.certificate_examination_center.dao.CustomerDao;
import com.pptk.certificate_examination_center.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
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

    @Override
    public Customer selectCustomerByRegistrationId(Integer registrationId) {
        String sql = "SELECT " +
                    "    kh.id_khach_hang, " +
                    "    kh.ho_ten, " +
                    "    kh.ten_co_quan, " +
                    "    kh.sdt, " +
                    "    kh.email, " +
                    "    kh.dia_chi, " +
                    "    kh.cccd, " +
                    "    kh.ngay_sinh, " +
                    "    kh.loai_khach_hang " +
                    "FROM phieu_dang_ky pdk " +
                    "JOIN khach_hang kh ON pdk.id_khach_hang = kh.id_khach_hang " +
                    "WHERE pdk.id_phieu_dang_ky = ?";

        try {
            return (Customer) entityManager
                    .createNativeQuery(sql, Customer.class)
                    .setParameter(1, registrationId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


}
