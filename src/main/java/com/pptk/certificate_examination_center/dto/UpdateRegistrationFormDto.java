package com.pptk.certificate_examination_center.dto;

public class UpdateRegistrationFormDto {
    private Integer formId;
    private Integer scheduleId; // ⚠️ không được viết sai thành schudeleId
    private Integer evidenceCode;

    public UpdateRegistrationFormDto() {} // ⚠️ BẮT BUỘC phải có constructor rỗng

    public UpdateRegistrationFormDto(Integer formId, Integer scheduleId, Integer evidenceCode) {
        this.formId = formId;
        this.scheduleId = scheduleId;
        this.evidenceCode = evidenceCode;
    }

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getEvidenceCode() {
        return evidenceCode;
    }

    public void setEvidenceCode(Integer evidenceCode) {
        this.evidenceCode = evidenceCode;
    }
}
