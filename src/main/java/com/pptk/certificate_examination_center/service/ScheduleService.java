package com.pptk.certificate_examination_center.service;

import java.util.List;
import com.pptk.certificate_examination_center.dto.ScheduleDto;

public interface ScheduleService {
    List<ScheduleDto> getScheduleByRegistrationId(Integer registrationId);
}
