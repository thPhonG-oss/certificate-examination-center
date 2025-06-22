package com.pptk.certificate_examination_center.service;

import java.util.List;
import com.pptk.certificate_examination_center.dto.ExamScheduleDto;

public interface ExamScheduleService {
    List<ExamScheduleDto> getScheduleByRegistrationId(Integer registrationId);
}
