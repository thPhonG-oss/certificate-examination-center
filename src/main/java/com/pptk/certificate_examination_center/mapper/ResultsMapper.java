package com.pptk.certificate_examination_center.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.pptk.certificate_examination_center.dto.ResultsExamDto;

@Component
public class ResultsMapper {

    public List<ResultsExamDto> mapToResultsDtoList(List<Object[]> results) {
        List<ResultsExamDto> dtoList = new ArrayList<>();
        for (Object[] result : results) {
            if (result != null && result.length == 4) {
                ResultsExamDto dto = new ResultsExamDto(
                        (Integer) result[0],  // idThiSinh
                        (String) result[1],   // tenChungChi
                        (String) result[2],    // diem
                        (Integer) result[3]
                );
                dtoList.add(dto);
            }
        }
        return dtoList;
    }
}