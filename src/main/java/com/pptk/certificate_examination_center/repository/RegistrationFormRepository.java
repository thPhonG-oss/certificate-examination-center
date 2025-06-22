package com.pptk.certificate_examination_center.repository;

import com.pptk.certificate_examination_center.entity.RegistrationForm;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface RegistrationFormRepository extends JpaRepository<RegistrationForm, Long> {
    // Additional query methods can be defined here if needed
    @Modifying
    @Transactional
    @Query( value =  "INSERT INTO phieu_dang_ky (id_nhan_vien, id_khach_hang, ngay_dang_ky)" +
            "VALUES (:employee_id, :customer_id, :registration_date)", nativeQuery = true)
    public void saveRegistrationForm(@Param("employee_id") Long employeeId,
                              @Param("customer_id") Long customerId,
                              @Param("registration_date") LocalDate registrationDate);

    @Query(value = "SELECT MAX(id_phieu_dang_ky) FROM phieu_dang_ky", nativeQuery = true)
    public Long getLastInsertedId();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO chi_tiet_phieu_dang_ky(id_phieu_dang_ky, id_lich_thi) VALUES (:registration_form_id, :schedule_id)", nativeQuery = true)
    public void updateRegisterDetails(@Param("registration_form_id") Long registration_form_id,
                               @Param("schedule_id") Long schedule_id);

    @Query(value = "SELECT * FROM phieu_dang_ky WHERE id_phieu_dang_ky = :registration_form_id", nativeQuery = true)
    public RegistrationForm getFormById(@Param("registration_form_id") Long registration_form_id);

}
