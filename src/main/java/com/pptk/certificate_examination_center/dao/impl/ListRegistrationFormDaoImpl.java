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
                    WHEN pd.lan_gia_han = 2
                        AND DATEDIFF(HOUR, GETDATE(),
                            CAST(CONCAT(CONVERT(VARCHAR, lt.ngay_thi, 23), ' ', CONVERT(VARCHAR, lt.gio_thi, 108)) AS DATETIME)
                       ) <= 24
                        AND DATEDIFF(HOUR, GETDATE(),
                            CAST(CONCAT(CONVERT(VARCHAR, lt.ngay_thi, 23), ' ', CONVERT(VARCHAR, lt.gio_thi, 108)) AS DATETIME)
                       ) > 0
                   THEN 0
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
               cc.id_chung_chi,
               cc.ten_chung_chi,
               
               pd.lan_gia_han  -- Cột thứ 11 (index 10)
               
           FROM phieu_dang_ky pd
           JOIN khach_hang kh ON pd.id_khach_hang = kh.id_khach_hang
           LEFT JOIN thi_sinh ts ON ts.id_phieu_dang_ky = pd.id_phieu_dang_ky
           LEFT JOIN lich_thi lt ON lt.id_lich_thi = pd.id_lich_thi
           LEFT JOIN chung_chi cc ON cc.id_chung_chi = lt.id_chung_chi
           LEFT JOIN hoa_don hd ON hd.id_phieu_dang_ky = pd.id_phieu_dang_ky;
        """;

        List<Object[]> rows = entityManager.createNativeQuery(sql).getResultList();
        List<ListRegistrationFormDto> result = new ArrayList<>();

        for (Object[] row : rows) {
            ListRegistrationFormDto dto = new ListRegistrationFormDto(
                row[0] != null ? ((Number) row[0]).intValue() : 2,                    // status
                String.valueOf(row[1]),                                              // customerID
                (String) row[2],                                                     // customerName
                row[3] != null ? ((java.sql.Date) row[3]).toLocalDate() : null,      // registerDate
                row[4] != null ? String.valueOf(row[4]) : null,                      // candidateID
                (String) row[5],                                                     // candidateName
                (String) row[6],                                                     // examSchedule
                row[7] != null ? String.valueOf(row[7]) : null,                      // registrationFormId
                row[8] != null ? String.valueOf(row[8]) : null,                      // certificateId
                (String) row[9],                                                     // certificateName
                row[10] != null ? ((Number) row[10]).intValue() : 0                  // extensionCount (THÊM THAM SỐ NÀY)
            );
            result.add(dto);
        }

        return result;
    }
}
