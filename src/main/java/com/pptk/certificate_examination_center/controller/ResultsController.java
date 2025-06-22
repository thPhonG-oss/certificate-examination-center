package com.pptk.certificate_examination_center.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pptk.certificate_examination_center.dto.DetailResultsDto;
import com.pptk.certificate_examination_center.dto.ResultsExamDto;
import com.pptk.certificate_examination_center.service.ResultsService;


@RestController
@RequestMapping("/api/results")
public class ResultsController {
    @Autowired
    private ResultsService resultsService;

    @GetMapping
    public List<ResultsExamDto> getAllExamResults() {
        return resultsService.getAllExamResults();
    }

    @GetMapping("/search")
    public List<ResultsExamDto> getExamResultsByThiSinhId(@RequestParam(required = false) Integer idThiSinh) {
        if (idThiSinh != null) {
            return resultsService.getExamResultsByThiSinhId(idThiSinh);
        }
        return resultsService.getAllExamResults();
    }

    @GetMapping("/detail")
    public DetailResultsDto getDetailResultsByKetQuaId(@RequestParam("idKetQua") Integer idKetQua) {
    return resultsService.getDetailResultsByKetQuaId(idKetQua);

    
}

    @PutMapping("/update-status")
    public ResponseEntity<String> updateTrangThaiNhan(
    @RequestParam("idKetQua") Integer idKetQua,
    @RequestParam("trangThaiNhan") Boolean trangThaiNhan
    ){
    resultsService.updateTrangThaiNhanById(idKetQua, trangThaiNhan);
    return ResponseEntity.ok("Cập nhật thành công");
    }

}




