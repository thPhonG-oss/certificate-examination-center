// package com.pptk.certificate_examination_center.repository;

// import java.util.List;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.stereotype.Repository;

// import com.pptk.certificate_examination_center.dto.ResultsExamDto;
// import com.pptk.certificate_examination_center.entity.Result;

// @Repository
// public interface ResultRepository extends JpaRepository<Result, Long> {
//     // Additional query methods can be defined here if needed
//     @Query("""
//         SELECT new com.example.dto.ResultsDto(ts.id, cc.tenChungChi, kq.diem)
//         FROM ket_qua kq
//         JOIN kq.id_phieu_du_thi pdt
//         JOIN pdt.thiSinh ts
//         JOIN ts.phieuDangKy pd
//         JOIN pd.chiTietPhieuDangKyList ctpdk
//         JOIN ctpdk.lichThi lt
//         JOIN lt.chungChi cc
//     """)
//     List<ResultsExamDto> layTatCaKetQuaThi();
// }
