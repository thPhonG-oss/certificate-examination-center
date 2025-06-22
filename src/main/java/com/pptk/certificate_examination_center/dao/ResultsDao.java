package com.pptk.certificate_examination_center.dao;

import java.util.List;

import com.pptk.certificate_examination_center.dto.DetailResultsDto;
import com.pptk.certificate_examination_center.dto.ResultsExamDto;

public interface  ResultsDao {
    List<ResultsExamDto> findAllExamResults();
    List<ResultsExamDto> findExamResultsByThiSinhId(Integer idThiSinh);
    DetailResultsDto findDetailResultsByKetQuaId(Integer idKetQua);
    void updateTrangThaiNhanById(Integer idKetQua, Boolean trangThaiNhan);


}