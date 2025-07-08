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
        
        // Cập nhật lịch thi trực tiếp trong phieu_dang_ky
        int rowsUpdated1 = entityManager.createNativeQuery("""
            UPDATE phieu_dang_ky
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

        // Tăng số lần gia hạn lên 1 sau khi cập nhật thành công
        int rowsUpdated3 = entityManager.createNativeQuery("""
            UPDATE phieu_dang_ky
            SET lan_gia_han = lan_gia_han + 1
            WHERE id_phieu_dang_ky = ?
        """)
        .setParameter(1, dto.getFormId())
        .executeUpdate();

        // Trả về true nếu tất cả 3 câu lệnh UPDATE đều thành công
        return rowsUpdated1 > 0 && rowsUpdated2 > 0 && rowsUpdated3 > 0;
    }
}
