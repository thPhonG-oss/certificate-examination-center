package com.pptk.certificate_examination_center.dao.impl;

import com.pptk.certificate_examination_center.dao.RegistrationFormDao;
import com.pptk.certificate_examination_center.dto.UpdateRegistrationFormDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class RegistrationFormDaoImpl implements RegistrationFormDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public boolean updateRegistrationForm(UpdateRegistrationFormDto dto) {
        // Cập nhật lịch thi trong chi_tiet_phieu_dang_ky
        int rowsUpdated1 = entityManager.createNativeQuery("""
            UPDATE chi_tiet_phieu_dang_ky
            SET id_lich_thi = ?
            WHERE id_phieu_dang_ky = ?
        """)
        .setParameter(1, dto.getScheduleId())
        .setParameter(2, dto.getFormId())
        .executeUpdate();

        // Cập nhật trạng thái gia hạn trong phieu_dang_ky
        int rowsUpdated2 = entityManager.createNativeQuery("""
            UPDATE phieu_dang_ky
            SET trang_thai_gia_han = CASE
                WHEN ? = 1 THEN 'FREE'
                ELSE 'PAID'
            END
            WHERE id_phieu_dang_ky = ?
        """)
        .setParameter(1, dto.getEvidenceCode())  // evidenceCode là 1 hoặc 0
        .setParameter(2, dto.getFormId())
        .executeUpdate();

        return rowsUpdated1 > 0 && rowsUpdated2 > 0;
    }
}
