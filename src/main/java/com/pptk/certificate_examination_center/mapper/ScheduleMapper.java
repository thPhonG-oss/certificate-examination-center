package com.pptk.certificate_examination_center.mapper;

import com.pptk.certificate_examination_center.dto.ExamScheduleDto;
import com.pptk.certificate_examination_center.entity.Schedule;
import com.pptk.certificate_examination_center.dto.ScheduleDto;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ScheduleMapper {

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static ExamScheduleDto toDto(Schedule entity) {
        ExamScheduleDto dto = new ExamScheduleDto();
        dto.setId(entity.getId());
        dto.setDate(entity.getDate().format(dateFormatter));
        dto.setTime(entity.getTime().toString());
        dto.setCertification_id(entity.getCertification_id());
        dto.setCurrentParticipants(entity.getCurrentParticipants());
        dto.setMaxParticipants(entity.getMaxParticipants());
        return dto;
    }

   public static Schedule toEntity(ScheduleDto scheduleDto){
        return new Schedule(scheduleDto.getDate(),scheduleDto.getTime(),scheduleDto.getCertification_id(),scheduleDto.getCurrentParticipants(),scheduleDto.getMaxParticipants());
    }
}


