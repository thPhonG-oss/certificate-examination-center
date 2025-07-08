package com.pptk.certificate_examination_center.service.impl;

import com.pptk.certificate_examination_center.dao.InvoiceDao;
import com.pptk.certificate_examination_center.dto.InvoiceDto;
import com.pptk.certificate_examination_center.dto.InvoiceInformationDto;
import com.pptk.certificate_examination_center.dto.UpdatePaymentInvoiceDto;
import com.pptk.certificate_examination_center.entity.Certificate;
import com.pptk.certificate_examination_center.entity.Invoice;
import com.pptk.certificate_examination_center.mapper.InvoiceMapper;
import com.pptk.certificate_examination_center.repository.InvoiceRepository;
import com.pptk.certificate_examination_center.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceDao invoiceDao;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Override
    public InvoiceDto getInvoiceInformation(Integer idInvoice) {
        InvoiceInformationDto tmp=invoiceDao.getInvoiceInformation(idInvoice);
        List<Certificate> certificates=invoiceDao.getCertificate(idInvoice);
        Double discount=0.0;
        Double payment=0.0;
        Double sumMoney=0.0;
        for( Certificate c : certificates){
            sumMoney+=c.getPrice();
        }
        if(tmp.getTypeKh().equals("ORGANIZATION")){
            if(tmp.getNumber()>20){
                discount=sumMoney*0.2;
            }else {
                discount = sumMoney * 0.15;
            }
        }
        payment=sumMoney-discount;
        return InvoiceMapper.toDto(tmp,sumMoney,discount,payment,certificates);
    }

    @Override
    public Invoice setInvoice(Invoice invoice) {
        Integer idPhieuDangKy = invoice.getRegistration_form_id().intValue();
        // B1: Kiểm tra PDK có tồn tại không
        if (!invoiceDao.existsPhieuDangKy(idPhieuDangKy)) {
            throw new RuntimeException("Phiếu đăng ký không tồn tại!");
        }

        // B2: Kiểm tra đã có hóa đơn chưa
        if (invoiceRepository.existsByPhieuDangKy_Id(idPhieuDangKy.longValue())) {
            throw new RuntimeException("Phiếu đăng ký này đã có hóa đơn!");
        }

        return invoiceRepository.save(invoice);
    }


    @Override
    public Invoice updatePaymentInvoice(Integer idInvoice, UpdatePaymentInvoiceDto payment_method) {
        Invoice invoice = invoiceRepository.findById(idInvoice.longValue())
                .orElseThrow(() -> new RuntimeException("Invoice not found with id = " + idInvoice));
        invoice.setPayment_method(payment_method.getPayment_method());
        return invoiceRepository.save(invoice);
    }

    @Override
    public List<Invoice> getAllInvoice() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice findOneInvoice(Integer idInvoice) {
        return invoiceRepository.findById(idInvoice.longValue())
                .orElse(null); // hoặc throw exception nếu không tìm thấy
    }

}
