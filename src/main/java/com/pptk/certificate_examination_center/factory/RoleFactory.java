package com.pptk.certificate_examination_center.factory;

import com.pptk.certificate_examination_center.entity.ERole;
import com.pptk.certificate_examination_center.entity.Role;
import com.pptk.certificate_examination_center.exception.RoleNotFoundException;
import com.pptk.certificate_examination_center.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleFactory {
    @Autowired
    RoleRepository roleRepository;

    public Role getInstance(String role) throws RoleNotFoundException {
        switch(role){
            case "admin" -> {
                return roleRepository.findByName(ERole.ROLE_ADMIN);
            }
            case "user" -> {
                return roleRepository.findByName(ERole.ROLE_USER);
            }
            default -> {
                throw new RoleNotFoundException("Role not found");
            }
        }
    }
}
