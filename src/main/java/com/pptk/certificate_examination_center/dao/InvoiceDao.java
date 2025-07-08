package com.pptk.certificate_examination_center.dao;

import com.pptk.certificate_examination_center.dto.InvoiceInformationDto;
import com.pptk.certificate_examination_center.entity.Certificate;

import java.util.List;

public interface InvoiceDao {
    public InvoiceInformationDto getInvoiceInformation(Integer idInvoice);
    public List<Certificate> getCertificate(Integer idInvoice);
    public boolean existsPhieuDangKy(Integer idPhieuDangKy);

}
