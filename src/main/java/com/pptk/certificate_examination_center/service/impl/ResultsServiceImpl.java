package com.pptk.certificate_examination_center.service.impl;

import java.util.List;

import com.pptk.certificate_examination_center.dao.ResultsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pptk.certificate_examination_center.dto.DetailResultsDto;
import com.pptk.certificate_examination_center.dto.ResultsExamDto;
import com.pptk.certificate_examination_center.service.ResultsService;

@Service
public class ResultsServiceImpl implements ResultsService {
    @Autowired
    private ResultsDao resultsDao;

    @Override
    public List<ResultsExamDto> getAllExamResults() {
        return resultsDao.findAllExamResults();
    }

    @Override
    public List<ResultsExamDto> getExamResultsByThiSinhId(Integer idThiSinh) {
        return resultsDao.findExamResultsByThiSinhId(idThiSinh);
    }

    @Override
    public DetailResultsDto getDetailResultsByKetQuaId(Integer idKetQua) {
        return resultsDao.findDetailResultsByKetQuaId(idKetQua);
    }

    @Override
    public void updateTrangThaiNhanById(Integer idKetQua, Boolean trangThaiNhan) {
        resultsDao.updateTrangThaiNhanById(idKetQua, trangThaiNhan);
    }

}