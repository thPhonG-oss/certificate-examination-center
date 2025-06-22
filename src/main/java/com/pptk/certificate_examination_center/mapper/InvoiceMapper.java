package com.pptk.certificate_examination_center.mapper;

import com.pptk.certificate_examination_center.dto.InvoiceDto;
import com.pptk.certificate_examination_center.dto.InvoiceInformationDto;
import com.pptk.certificate_examination_center.entity.Certificate;

import java.math.BigDecimal;
import java.util.List;

public class InvoiceMapper {
    public static InvoiceDto toDto(InvoiceInformationDto invoiceInformationDto , Double sumMoney, Double discount, Double payment, List<Certificate> certificates){
        if(invoiceInformationDto==null) {
            return null;
        }
        return new InvoiceDto(invoiceInformationDto.getIdPdk(), invoiceInformationDto.getIdKh(), invoiceInformationDto.getTypeKh(), sumMoney,discount,payment,certificates);
    }
}
