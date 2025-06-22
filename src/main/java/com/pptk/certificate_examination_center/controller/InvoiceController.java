package com.pptk.certificate_examination_center.controller;

import com.pptk.certificate_examination_center.dao.InvoiceDao;
import com.pptk.certificate_examination_center.dto.UpdatePaymentInvoiceDto;
import com.pptk.certificate_examination_center.entity.Invoice;
import com.pptk.certificate_examination_center.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;
    // lấy thông tin khi gửi id
    @GetMapping ("/api/invoice")
    public ResponseEntity<Object> setInvoice(){
        return ResponseEntity.ok(invoiceService.getAllInvoice());
    }
    @GetMapping ("/invoice/{idInvoice}")
    public ResponseEntity<Object> findOneInvoice(@PathVariable("idInvoice") Integer idInvoice){
        return ResponseEntity.ok(invoiceService.findOneInvoice(idInvoice));
    }
    @RequestMapping("/invoice_information/{idInvoice}")
    public ResponseEntity<Object> getInvoiceInformation(@PathVariable("idInvoice") Integer idInvoice){
        return ResponseEntity.ok(invoiceService.getInvoiceInformation(idInvoice));
    }
    // tao hóa đơn
    @PostMapping("/invoice/new_invoice")
    public ResponseEntity<Object> setInvoice(@RequestBody Invoice invoice){
        return ResponseEntity.ok(invoiceService.setInvoice(invoice));
    }
    // sửa hóa đơn
    @PostMapping("/invoice/update/{idInvoice}")
    public ResponseEntity<Object> setInvoice(@PathVariable("idInvoice") Integer idInvoice,@RequestBody UpdatePaymentInvoiceDto payment_method) {
        return ResponseEntity.ok(invoiceService.updatePaymentInvoice(idInvoice, payment_method));
    }
}
