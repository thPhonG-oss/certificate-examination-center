package com.pptk.certificate_examination_center.dao.impl;

import com.pptk.certificate_examination_center.dto.ListRegistrationFormDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ListRegistrationFormDaoImpl implements com.pptk.certificate_examination_center.dao.ListRegistrationFormDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ListRegistrationFormDto> selecListRegistrationForms() {
        String sql = """
            SELECT 
                CASE 
                    WHEN pd.trang_thai_gia_han IS NOT NULL THEN 0
                    WHEN hd.trang_thai = 'PAID' THEN 1
                    ELSE 2
                END AS trang_thai_dac_biet,
                kh.id_khach_hang,
                kh.ho_ten AS ho_ten_khach_hang,
                pd.ngay_dang_ky,
                ts.id_thi_sinh,
                ts.ho_ten AS ho_ten_thi_sinh,
                CONCAT(CONVERT(VARCHAR, lt.ngay_thi, 23), ' ', CONVERT(VARCHAR, lt.gio_thi, 108)) AS thoi_gian_thi,
                pd.id_phieu_dang_ky,
                cc.ten_chung_chi
            FROM phieu_dang_ky pd
            JOIN khach_hang kh ON pd.id_khach_hang = kh.id_khach_hang
            LEFT JOIN hoa_don hd ON pd.id_phieu_dang_ky = hd.id_phieu_dang_ky
            LEFT JOIN thi_sinh ts ON pd.id_phieu_dang_ky = ts.id_phieu_dang_ky
            LEFT JOIN chi_tiet_phieu_dang_ky ct ON pd.id_phieu_dang_ky = ct.id_phieu_dang_ky
            LEFT JOIN lich_thi lt ON ct.id_lich_thi = lt.id_lich_thi
            LEFT JOIN chung_chi cc ON lt.id_chung_chi = cc.id_chung_chi
            WHERE kh.ten_co_quan IS NULL;

        """;

        List<Object[]> rows = entityManager.createNativeQuery(sql).getResultList();
        List<ListRegistrationFormDto> result = new ArrayList<>();

        for (Object[] row : rows) {
            ListRegistrationFormDto dto = new ListRegistrationFormDto(
            row[0] != null ? ((Number) row[0]).intValue() : 2,               // status
            String.valueOf(row[1]),                                          // customerID
            (String) row[2],                                                 // customerName
            row[3] != null ? ((java.sql.Date) row[3]).toLocalDate() : null,  // registerDate
            row[4] != null ? String.valueOf(row[4]) : null,                  // candidateID
            (String) row[5],                                                 // candidateName
            (String) row[6],                                                 // examSchedule
            row[7] != null ? String.valueOf(row[7]) : null,                  // registrationFormId
            (String) row[8]                                                  // certificateName
        );


            result.add(dto);
        }

        return result;
    }
}
