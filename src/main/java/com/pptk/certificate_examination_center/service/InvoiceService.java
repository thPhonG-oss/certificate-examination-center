package com.pptk.certificate_examination_center.service;

import com.pptk.certificate_examination_center.dao.InvoiceDao;
import com.pptk.certificate_examination_center.dto.InvoiceDto;
import com.pptk.certificate_examination_center.dto.UpdatePaymentInvoiceDto;
import com.pptk.certificate_examination_center.entity.Invoice;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface InvoiceService {
    public InvoiceDto getInvoiceInformation(Integer idInvoice);
    public Invoice setInvoice(Invoice invoice);
    public Invoice updatePaymentInvoice(Integer idInvoice, UpdatePaymentInvoiceDto payment_method);
    public List<Invoice> getAllInvoice();
    public Invoice findOneInvoice(Integer idInvoice);
}
