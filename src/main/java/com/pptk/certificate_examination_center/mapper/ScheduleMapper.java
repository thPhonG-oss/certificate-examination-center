package com.pptk.certificate_examination_center.mapper;

import com.pptk.certificate_examination_center.dto.ScheduleDto;
import com.pptk.certificate_examination_center.entity.Schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ScheduleMapper {

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static ScheduleDto toDto(Schedule entity) {
        ScheduleDto dto = new ScheduleDto();
        dto.setId(entity.getId());
        dto.setDate(entity.getDate().format(dateFormatter));
        dto.setTime(entity.getTime().toString());
        dto.setCertification_id(entity.getCertification_id());
        dto.setCurrentParticipants(entity.getCurrentParticipants());
        dto.setMaxParticipants(entity.getMaxParticipants());
        return dto;
    }

    public static Schedule toEntity(ScheduleDto dto) {
        Schedule entity = new Schedule();
        entity.setId(dto.getId());
        entity.setDate(LocalDate.parse(dto.getDate(), dateFormatter));
        entity.setTime(LocalTime.parse(dto.getTime()));
        entity.setCertification_id(dto.getCertification_id());
        entity.setCurrentParticipants(dto.getCurrentParticipants());
        entity.setMaxParticipants(dto.getMaxParticipants());
        return entity;
    }
}
