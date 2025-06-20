package com.pptk.certificate_examination_center.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pptk.certificate_examination_center.dao.ListRegistrationFormDao;
import com.pptk.certificate_examination_center.service.ListRegistrationFormService;
import com.pptk.certificate_examination_center.dto.ListRegistrationFormDto;
import java.util.List;
@Service
public class ListRegistrationFormServiceImpl implements ListRegistrationFormService {

    @Autowired
    private ListRegistrationFormDao listRegistrationFormDao;

    @Override
    public List<ListRegistrationFormDto> getListRegistrationForms() {
        return listRegistrationFormDao.selecListRegistrationForms();
    }
    
}
