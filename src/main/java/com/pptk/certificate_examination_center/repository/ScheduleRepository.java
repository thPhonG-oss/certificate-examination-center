package com.pptk.certificate_examination_center.repository;

import com.pptk.certificate_examination_center.entity.Schedule;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    // Additional query methods can be defined here if needed
    @Transactional
    @Modifying
    @Query(
        value = "UPDATE lich_thi SET so_luong_dk = so_luong_dk + 1 WHERE id_lich_thi = :id_lich_thi AND so_luong_dk < sl_toi_da",
        nativeQuery = true
    )
    public void updateNumberOfCandidatesForIndividual(@Param("id_lich_thi") Long schedule_id);

    @Query(value = "SELECT s.id_lich_thi, s.ngay_thi, s.gio_thi, cc.ten_chung_chi, " +
            "s.so_luong_dk, s.sl_toi_da, s.id_chung_chi " +
            "FROM lich_thi s " +
            "INNER JOIN chung_chi cc ON s.id_chung_chi = cc.id_chung_chi " +
            "WHERE s.so_luong_dk < s.sl_toi_da",
            nativeQuery = true)
    List<Object[]> findAllSchedulesWithCertificates();
}
