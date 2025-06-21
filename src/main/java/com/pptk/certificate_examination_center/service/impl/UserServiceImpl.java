package com.pptk.certificate_examination_center.service.impl;

import com.pptk.certificate_examination_center.entity.Role;
import com.pptk.certificate_examination_center.entity.User;
import com.pptk.certificate_examination_center.repository.UserRepository;
import com.pptk.certificate_examination_center.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl() {
        super();
    }

    @Override
    public boolean existByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Set<Role> getRolesByUserId(Long userId) {
        return userRepository.findRolesByUserId(userId);
    }

    @Override
    @Transactional
    @Modifying
    public void addUserRole(Long userId, Long roleId) {
        userRepository.addUserRole(userId, roleId);
    }
}
