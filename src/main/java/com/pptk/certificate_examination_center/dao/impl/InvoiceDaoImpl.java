package com.pptk.certificate_examination_center.dao.impl;

import com.pptk.certificate_examination_center.dao.InvoiceDao;
import com.pptk.certificate_examination_center.dto.InvoiceInformationDto;
import com.pptk.certificate_examination_center.entity.Certificate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import jakarta.persistence.Query; // hoặc javax.persistence.Query tùy version
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class InvoiceDaoImpl implements InvoiceDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public InvoiceInformationDto getInvoiceInformation(Integer idInvoice) {
        String sql= """
                SELECT
                    pdk.id_phieu_dang_ky,
                    kh.id_khach_hang,
                    kh.loai_khach_hang,
                    COUNT(ts.id_thi_sinh) AS so_luong_thi_sinh
                FROM phieu_dang_ky pdk
                JOIN khach_hang kh ON pdk.id_khach_hang = kh.id_khach_hang
                JOIN thi_sinh ts ON ts.id_phieu_dang_ky = pdk.id_phieu_dang_ky
                WHERE pdk.id_phieu_dang_ky = ?
                GROUP BY pdk.id_phieu_dang_ky, kh.id_khach_hang, kh.loai_khach_hang;
                """;
        Object[] result = (Object[]) entityManager
                .createNativeQuery(sql)
                .setParameter(1, idInvoice)
                .getSingleResult();
        return new InvoiceInformationDto((Integer) result[0],
                (Integer) result[1],
                (String) result[2],
                ((Number) result[3]).longValue());
    }
    @Override
    public List<Certificate> getCertificate(Integer idInvoice){
        String sql= """
                Select cc.*
                from phieu_dang_ky pdk join lich_thi lt on lt.id_lich_thi= pdk.id_phieu_dang_ky
                join chung_chi cc on cc.id_chung_chi=lt.id_chung_chi
                where pdk.id_phieu_dang_ky=?;
                """;
        return (List<Certificate>) entityManager
                .createNativeQuery(sql, Certificate.class)
                .setParameter(1, idInvoice)
                .getResultList();
    }
    @Override
    public boolean existsPhieuDangKy(Integer idPhieuDangKy) {
        String sql = "SELECT COUNT(*) FROM phieu_dang_ky WHERE id_phieu_dang_ky = ?";
        Number count = (Number) entityManager.createNativeQuery(sql)
                .setParameter(1, idPhieuDangKy)
                .getSingleResult();
        return count.intValue() > 0;
    }

}
