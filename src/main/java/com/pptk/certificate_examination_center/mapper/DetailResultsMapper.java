package com.pptk.certificate_examination_center.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.pptk.certificate_examination_center.dto.DetailResultsDto;

@Component
public class DetailResultsMapper {

    public DetailResultsDto mapToDetailDto(Object[] row) {
        DetailResultsDto dto = new DetailResultsDto();

        dto.setName((String) row[0]);
        dto.setEmail((String) row[1]);
        dto.setPhone((String) row[2]);
        dto.setTen_chung_chi((String) row[3]);
        dto.setNgay_thi(row[4] != null ? row[4].toString() : null);
        dto.setDiem((String) row[5]);
        dto.setChitietketqua((String) row[6]); // Assuming this is the correct mapping
        dto.setTrangthai((String) row[7]);
        dto.setTrang_thai_nhan((Boolean) row[8] ? 1 : 0);
        dto.setId_ket_qua((Integer) row[9]);

        return dto;
    }

    public List<DetailResultsDto> mapToDetailDtoList(List<Object[]> resultList) {
        return resultList.stream()
                .map(this::mapToDetailDto)
                .collect(Collectors.toList());
    }
}