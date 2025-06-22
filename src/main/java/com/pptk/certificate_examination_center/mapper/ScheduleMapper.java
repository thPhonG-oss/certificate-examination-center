package com.pptk.certificate_examination_center.mapper;

import com.pptk.certificate_examination_center.dto.ScheduleDto;
import com.pptk.certificate_examination_center.entity.Schedule;

public class ScheduleMapper {
    public static Schedule toEntity(ScheduleDto scheduleDto){
        return new Schedule(scheduleDto.getDate(),scheduleDto.getTime(),scheduleDto.getCertification_id(),scheduleDto.getCurrentParticipants(),scheduleDto.getMaxParticipants());
    }
}
