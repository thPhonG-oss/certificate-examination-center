package com.pptk.certificate_examination_center.service;

import java.util.List;

import com.pptk.certificate_examination_center.dto.DetailResultsDto;
import com.pptk.certificate_examination_center.dto.ResultsExamDto;

public interface ResultsService {
    List<ResultsExamDto> getAllExamResults();
    List<ResultsExamDto> getExamResultsByThiSinhId(Integer idThiSinh);
    DetailResultsDto getDetailResultsByKetQuaId(Integer idKetQua);
    void updateTrangThaiNhanById(Integer idKetQua, Boolean trangThaiNhan);

}