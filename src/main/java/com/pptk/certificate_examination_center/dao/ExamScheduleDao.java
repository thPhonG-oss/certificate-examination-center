package com.pptk.certificate_examination_center.dao;

import java.util.List;
import com.pptk.certificate_examination_center.dto.ExamScheduleDto;

public interface ExamScheduleDao {
    List<ExamScheduleDto> selectScheduleByRegistrationId(Integer certificateId);
}
