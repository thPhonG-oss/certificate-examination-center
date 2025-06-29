package com.pptk.certificate_examination_center.repository;

import com.pptk.certificate_examination_center.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    // Additional query methods can be defined here if needed
    @Modifying
    @Query(value = "INSERT INTO thi_sinh (id_phieu_dang_ky, ho_ten, gioi_tinh, ngay_sinh, sdt, email, dia_chi, cccd) " +
            "VALUES (:id_registration_form, :name, :gender, :dob, :phoneNumber, :email, :address, :citizen_id)", nativeQuery = true)
    public void saveCandidate(
            @Param("id_registration_form") Long id_registration_form,
            @Param("name") String name,
            @Param("gender") String gender,
            @Param("dob") LocalDate dob,
            @Param("phoneNumber") String phoneNumber,
            @Param("email") String email,
            @Param("address") String address,
            @Param("citizen_id") String citizen_id
            );
}
