package com.pptk.certificate_examination_center.service;

import com.pptk.certificate_examination_center.entity.Role;
import com.pptk.certificate_examination_center.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    boolean existByUsername(String username);
    boolean existByEmail(String email);
    void save(User user);
    Set<Role> getRolesByUserId(Long userId);
    void addUserRole(Long userId, Long roleId);
}
