package com.pptk.certificate_examination_center.dao;

import java.util.List;
import com.pptk.certificate_examination_center.dto.ScheduleDto;

public interface ScheduleDao {
    List<ScheduleDto> selectScheduleByRegistrationId(Integer registrationId);
}
