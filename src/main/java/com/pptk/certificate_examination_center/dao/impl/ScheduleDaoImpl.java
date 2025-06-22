package com.pptk.certificate_examination_center.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import com.pptk.certificate_examination_center.dao.ScheduleDao;
import com.pptk.certificate_examination_center.dto.ExamScheduleDto;

@Repository
public class ScheduleDaoImpl implements ScheduleDao {

    @PersistenceContext
    private EntityManager entityManager;

   @Override
    public List<ExamScheduleDto> selectScheduleByRegistrationId(Integer registrationId) {
        String sql = """
                SELECT lt.id_lich_thi, lt.ngay_thi, lt.gio_thi, lt.id_chung_chi, lt.so_luong_dk, lt.sl_toi_da
                FROM lich_thi lt
                WHERE lt.id_chung_chi IN (
                    SELECT lt2.id_chung_chi
                    FROM chi_tiet_phieu_dang_ky ct
                    JOIN lich_thi lt2 ON ct.id_lich_thi = lt2.id_lich_thi
                    WHERE ct.id_phieu_dang_ky = ?
                )
                AND lt.id_lich_thi NOT IN (
                    SELECT ct.id_lich_thi
                    FROM chi_tiet_phieu_dang_ky ct
                    WHERE ct.id_phieu_dang_ky = ?
                );

            """;

        @SuppressWarnings("unchecked")
        List<Object[]> results = entityManager
                .createNativeQuery(sql)
                .setParameter(1, registrationId)
                .setParameter(2, registrationId)
                .getResultList();

        return results.stream().map(row -> {
            Long id = ((Number) row[0]).longValue();
            String date = row[1].toString(); // yyyy-MM-dd
            String time = row[2].toString(); // HH:mm:ss
            Long certificationId = ((Number) row[3]).longValue();
            Integer currentParticipants = ((Number) row[4]).intValue();
            Integer maxParticipants = ((Number) row[5]).intValue();

            return new ExamScheduleDto(id, date, time, certificationId, currentParticipants, maxParticipants);
        }).collect(Collectors.toList());
    }

}
