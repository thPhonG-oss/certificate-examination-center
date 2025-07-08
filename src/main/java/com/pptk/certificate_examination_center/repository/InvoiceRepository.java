package com.pptk.certificate_examination_center.repository;

import com.pptk.certificate_examination_center.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    @Query("SELECT COUNT(i) > 0 FROM Invoice i WHERE i.registration_form_id = :idPhieuDangKy")
    boolean existsByPhieuDangKy_Id(@Param("idPhieuDangKy") Long idPhieuDangKy);
}
